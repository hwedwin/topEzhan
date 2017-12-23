package com.topaiebiz.member.manage.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum MemberManagerExceptionEnum implements ExceptionInfo {

    MEMBERGRADE_GRADECODE_NOT_EXIST("5000001", "Member Grade gradeCode does not exist!"),
    MEMBER_ID_NOT_EXIST("5000002", "Member id does not exist!"),
    MEMBERGRADE_GRADEID_NOT_EXIST("5000003", "Member Grade id does not exist!"),
    MEMBER_APPLY_ID_NOT_EXIST("5000004", "Member Apply id does not exist!"),
    MEMBER_APPLY_STATUS_ERROR("5000005","Member Apply status error!" ),
    MEMBER_GRADE_ERROR("5000006","Member Apply grade error!" ),
    MEMBER_PARENT_MEMBERID_NOT_EXIST("5000007", "Member parent id does not exist!"),
    MEMBER_ADDDIRECTOR_IDENTITY_STATUS_ERROR("5000008", "Member addDirector identity status error!"),
    MEMBER_TELEPHONE_NOT_EXIST("5000009", "Member telephone does not exist!"),
    MEMBER_ADDSHOPKEEPER_STATUS_ERROR("5000010","Member shopkeeper identity status error!" ),
    MEMBER_LABELTYPE_ID_NOT_EXIST("5000011", "Member Label Type id does not exist!"),
    MEMBER_LABEL_ID_NOT_EXIST("5000012", "Member Label id does not exist!"),
    MEMBER_LABEL_OPTTYPE_STATUS_ERROR("5000013", "Member Label OptType status error!"),
    MEMBER_LABELTYPE_NAME_NOT_EXIST("5000014","Member Label Type Name does not exist!" ),
    MEMBER_LABEL_NAME_NOT_EXIST("5000015","Member Label Name does not exist!" ),
    MEMBER_LABELTYPE_NAME_HAVE_EXIST("5000016","Member Label Type Name have already exist!" ),
    MEMBER_LABEL_NAME_HAVE_EXIST("5000017", "Member Label Name have already exist!");

    /**
     * 异常代码。
     */
    private String code;

    /**
     * 异常对应的默认提示信息。
     */
    private String defaultMessage;

    /**
     * 异常对应的原始提示信息。
     */
    private String originalMessage;

    /**
     * 当前请求的URL。
     */
    private String requestUrl;

    /**
     * 默认的转向（重定向）的URL，默认为空。
     */
    private String defaultRedirectUrl = "";

    /**
     * 异常对应的响应数据。
     */
    private Object data;

    /**
     * Description: 根据异常的代码、默认提示信息构建一个异常信息对象。
     * <p>
     * Author: Scott.Yang
     *
     * @param code           异常的代码。
     * @param defaultMessage 异常的默认提示信息。
     */
    MemberManagerExceptionEnum(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public String getOriginalMessage() {
        return originalMessage;
    }

    @Override
    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    @Override
    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String getDefaultRedirectUrl() {
        return defaultRedirectUrl;
    }

    @Override
    public void setDefaultRedirectUrl(String defaultRedirectUrl) {
        this.defaultRedirectUrl = defaultRedirectUrl;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

}
