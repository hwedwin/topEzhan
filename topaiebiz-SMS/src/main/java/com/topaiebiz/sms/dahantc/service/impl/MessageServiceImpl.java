package com.topaiebiz.sms.dahantc.service.impl;

import com.dahantc.api.sms.json.JSONHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.sms.dahantc.dao.CaptchaDao;
import com.topaiebiz.sms.dahantc.dto.MessageDto;
import com.topaiebiz.sms.dahantc.entity.CaptchaEntity;
import com.topaiebiz.sms.dahantc.exception.MessageExceptionEnum;
import com.topaiebiz.sms.dahantc.service.MessageService;
import com.topaiebiz.sms.dahantc.util.DateUtil;
import com.topaiebiz.sms.dahantc.util.RedisClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;


@Transactional
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private CaptchaDao captchaDao;

    @Resource
    private RedisClient redisClient;


    // 用户名（必填）dh25231  行业账号 ， 密码（必填） xT6roGU0
    private static String account = "dh25231";
    private static String password = "xT6roGU0";

    // 用户名（必填） 营销帐号 ， 密码（必填）
    private static String bAccount = "dh25232";
    private static String bPassword = "D3v4p79a";

    //签名和子签名，由大汗三通提供
    private static String sign = "【贝因美亲子e站】"; // 短信签名（必填） 【大汉三通】
    private static String subcode = ""; // 子号码（可选）
//	public static String msgid = UUID.randomUUID().toString().replace("-", ""); // 短信id，查询短信状态报告时需要，（可选）
//	public static String sendtime = ""; // 定时发送时间（可选）

    // 发送次数限制
    @Value("${sms.send.limit-times-oneday}")
    private int SMSCODE_SEND_TIMES_ONEDAY;
    // 检验错误次数限制
    @Value("${sms.check.limit-times-oneday}")
    private int SMSCODE_CHECK_TIMES_ONEDAY;
    // 验证码失效时间
    @Value("${sms.expire-time}")
    private int SMSCODE_EXPIRE_TIME;
    // 发送短信频率时间限制
    @Value("${sms.limit-time}")
    private int SMSCODE_LIMIT_TIME;
    // 是否开启短信频率时间限制
    @Value("${sms.is-limit}")
    private boolean IS_SMSCODE_LIMIT;


    private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public MessageDto sendCaptcha(String telephone, String content, String captcha) throws Exception {
        if (IS_SMSCODE_LIMIT) {
            this.checkData(telephone);
        }
        MessageDto messageDto = this.sendSms("1", telephone, content);
        if (!"0".equals(messageDto.getResult())) {
            throw new GlobalException(MessageExceptionEnum.SEND_MESSAGE_FAILURE);
        }
        // captcha存入redis中
        if (IS_SMSCODE_LIMIT) {
            redisClient.set(telephone, captcha, SMSCODE_EXPIRE_TIME);
            logger.info("phone={}, 发送的验证码={}", telephone, captcha);
        } else {
            this.saveCaptcha(telephone, captcha);
        }
        return messageDto;
    }

    /**
     * 检验发送频率
     *
     * @param telephone
     */
    private void checkData(String telephone) {
        if (StringUtils.isNotBlank(redisClient.get(telephone + "_LIMIT_TIME"))) {
            logger.error("phone={}, 发送过于频繁 请稍后再试!", telephone, SMSCODE_SEND_TIMES_ONEDAY);
            throw new GlobalException(MessageExceptionEnum.SEND_MESSAGE_LIMIT_FAIL);
        }
        Integer s = null;
        String TODAY_LIMIT_TIMES = redisClient.get(DateUtil.nowDateToString() + telephone + "_TODAY_LIMIT_TIMES");
        if (StringUtils.isNotBlank(TODAY_LIMIT_TIMES)) {
            s = Integer.valueOf(TODAY_LIMIT_TIMES);
        }
        if (s != null && s > SMSCODE_SEND_TIMES_ONEDAY) {
            logger.error("phone={}, 短信发送次数超过{}次, 请稍后再试!", telephone, SMSCODE_SEND_TIMES_ONEDAY);
            throw new GlobalException(MessageExceptionEnum.SEND_MESSAGE_LIMIT_FAIL);
        }

        // 待校验数据存入redis中
        redisClient.set(telephone + "_LIMIT_TIME", new Date().getTime(), SMSCODE_LIMIT_TIME);
        redisClient.incr(DateUtil.nowDateToString() + telephone + "_TODAY_LIMIT_TIMES");
        redisClient.expire(DateUtil.nowDateToString() + telephone + "_TODAY_LIMIT_TIMES", (int) (86400 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE)));

    }

    /**
     * Description 发送信息
     * <p>
     * Author Aaron.Xue
     *
     * @param type    发送账号类型 1 为普通账号    2为营销账号
     * @param phone   需发送短信的手机号，多个以英文逗号隔开
     * @param content 发送的内容
     * @return
     * @throws Exception
     */
    private MessageDto sendSms(String type, String phone, String content) throws Exception {
        String currentAccount = account;
        String currentPassword = password;
        //1为普通用户
        if ("2".equals(type)) {
            currentAccount = bAccount;
            currentPassword = bPassword;
        }
        JSONHttpClient jsonHttpClient = new JSONHttpClient("http://www.dh3t.com");
        jsonHttpClient.setRetryCount(1);
        String sendhRes = jsonHttpClient.sendSms(currentAccount, currentPassword, phone, content, sign, subcode);
        //{"msgid":"","result":"2","desc":"密码错误","failPhones":""}
        ObjectMapper mapper = new ObjectMapper();
        MessageDto messageDto = mapper.readValue(sendhRes, MessageDto.class);
        return messageDto;
    }

    /**
     * Description： 保存验证码到数据库中
     * <p>
     * Author Aaron.Xue
     *
     * @param telephone 电话号码
     * @param captcha   验证码
     */
    private void saveCaptcha(String telephone, String captcha) {
        CaptchaEntity captchaEntity = captchaDao.selectByTelephone(telephone);
        //失效时间10分钟 600s
        DateTime dateTime = new DateTime().secondOfMinute().addToCopy(600);
        Date lapseTime = dateTime.toDate();
        if (null == captchaEntity) {
            //如果是空，直接插入一条数据
            captchaEntity = new CaptchaEntity();
            captchaEntity.setCaptcha(captcha);
            captchaEntity.setTelephone(telephone);
            captchaEntity.setLapseTime(lapseTime);
            captchaEntity.setLastModifiedTime(new Date());
            captchaDao.insert(captchaEntity);
        } else {
            captchaEntity.setCaptcha(captcha);
            captchaEntity.setLapseTime(lapseTime);
            captchaEntity.setLastModifiedTime(new Date());
            captchaDao.updateById(captchaEntity);
        }
    }

    @Override
    public boolean verifyCaptcha(String telephone, String captcha) throws GlobalException {
        if (!IS_SMSCODE_LIMIT) {
//            CaptchaEntity captchaEntity = captchaDao.selectByTelephone(telephone);
            //校验验证码不正确
//		if(null == captchaEntity || null == captchaEntity.getCaptcha() || "".equals(captchaEntity.getCaptcha()) || !captcha.equals(captchaEntity.getCaptcha())) {
//			//如果不正确，抛异常
//			throw new GlobalException(MessageExceptionEnum.CAPTCHA_IS_ERROR);
//		}else {
//			//校验是否过期
//			if(this.compareDate(new Date(), captchaEntity.getLapseTime())) {
//				//抛出过期异常
//				throw new GlobalException(MessageExceptionEnum.CAPTCHA_STALE_DATED);
//			}
//		}
              return true;
        }
        Integer s = null;
        String FAIL_OVER_TIMES = redisClient.get(DateUtil.nowDateToString() + telephone);
        if (StringUtils.isNotBlank(FAIL_OVER_TIMES)) {
            s = Integer.valueOf(FAIL_OVER_TIMES);
        }
        if (s != null && s > SMSCODE_CHECK_TIMES_ONEDAY) {
            logger.error("phone={}, 验证短信失败次数超过{}次, 锁定账户.", telephone, SMSCODE_CHECK_TIMES_ONEDAY);
            throw new GlobalException(MessageExceptionEnum.SEND_MESSAGE_CHECK_FAILURE);
        }

        String smsCode = redisClient.get(telephone.trim());
        if (captcha.equals(smsCode)) {
            redisClient.delete(telephone);
            return true;
        } else {
            redisClient.incr(DateUtil.nowDateToString() + telephone);
            redisClient.expire(DateUtil.nowDateToString() + telephone, (int) (86400 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE)));
            return false;
        }
    }

    /**
     * Description： 比较日期大小  ,判断d1是否大于d2
     * <p>
     * Author Aaron.Xue
     *
     * @param d1
     * @param d2
     * @return
     */
    private boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }
}
