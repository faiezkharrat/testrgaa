package com.tanaguru.domain.constant;

public enum CustomError {

    ACCESS_DENIED,
    APP_ROLE_NOT_FOUND,
    APP_ACCOUNT_TYPE_NOT_FOUND,
    AUDIT_NOT_FOUND,
    AUDIT_PARAMETER_NO_DEFAULT_VALUE,
    AUDIT_PARAMETERS_NOT_FOUND,
    AUDIT_SCHEDULER_NOT_FOUND,
    CANNOT_DELETE_RUNNING_AUDIT,
    FORBIDDEN_STOP_AUDIT,
    CANNOT_STOP_FINISHED_AUDIT,
    CANNOT_STOP_AUDIT_WITH_CURRENT_CONFIGURATION,
    AUTHENTICATION_FAILED,
    
    CANNOT_ACCESS_ELEMENT_RESULTS_FOR_TEST,
    CANNOT_ACCESS_PAGE_CONTENT_FOR_AUDIT,
    CANNOT_ACCESS_PAGE_CONTENT_FOR_PAGE,
    CANNOT_ACCESS_PAGES_FOR_AUDIT,
    CANNOT_ACCESS_RESULT_AUDIT,
    CANNOT_ACCESS_SCHEDULER,
    
    CANNOT_CREATE_MULTIPLE_REFERENCES,
    CANNOT_CREATE_MULTIPLE_USER_CONTRACT,
    CANNOT_CREATE_PROJECT_FOR_THIS_CONTRACT,

    CANNOT_DELETE_CONTRACT_OWNER,
    CANNOT_DELETE_CURRENT_USER,
    
    CANNOT_FIND_CONTRACT_ROLE,
    CANNOT_FIND_MAIN_HIERARCHY_RESULT_FOR_PAGE,
    CANNOT_FIND_MAIN_REFERENCE_AUDIT,
    CANNOT_FIND_TEST_HIERARCHY_FOR_PAGE,

    CANNOT_FIND_AUDITPARAMETER,
    
    CANNOT_MODIFY_AUDIT_ASSOCIATED_SCHEDULER,
    CANNOT_MODIFY_USER_PASSWORD,
    CANNOT_MODIFY_PROJECT_FOR_THIS_CONTRACT,
    
    CANNOT_PROMOTE_CONTRACT_OWNER,
    CANNOT_PROMOTE_YOURSELF,
    
    CANNOT_SHOW_AUDIT,
    CANNOT_GENERATE_API_KEY,
    
    CONTRACT_DATE_PASSED,
    CONTRACT_NOT_FOUND,
    
    CURRENT_USER_NO_ACCESS_SCHEDULER,
    EMAIL_ALREADY_EXISTS,
    ERROR_SENDING_EMAIL,
    EMAIL_NOT_FOUND,
    
    INVALID_DOMAIN,
    INVALID_PASSWORD,
    INVALID_SCENARIO_CONTENT,
    INVALID_VALUE_PARAMETER,

    NO_MAIN_REFERENCE,
    NO_USABLE_REFERENCE,
    PAGE_NOT_FOUND,
    
    PROJECT_CANNOT_PROMOTE_USER,
    PROJECT_LIMIT_FOR_CONTRACT,
    PROJECT_NOT_FOUND,
    PROJECT_ROLE_NOT_FOUND,
    AUDIT_TYPE_NOT_ACCEPTED_BY_PROJECT,
    
    RESOURCE_NOT_FOUND,
    
    SCENARIO_NOT_FOUND,
    SCHEDULER_ALREADY_EXISTS_FOR_AUDIT,
    
    TANAGURU_TEST_NOT_FOUND,
    TEST_HIERARCHY_NOT_FOUND,
    TEST_HIERARCHY_RESULT_NOT_FOUND,
    TEST_RESULT_NOT_FOUND,
    TIMER_VALUE_TOO_SHORT,
    
    USER_CANNOT_ACCESS_PAGE_RESULT,
    USER_CANNOT_ACCESS_RESOURCE,
    USER_CANNOT_ACCESS_SCENARIO,
    USER_CANNOT_DELETE_RESOURCE,
    USER_CANNOT_DELETE_SCENARIO,
    
    USER_NOT_CURRENT_MEMBER_CONTRACT,
    USER_NOT_FOUND,
    USER_NOT_FOUND_FOR_CONTRACT,
    USER_NOT_FOUND_FOR_PROJECT,
    USERNAME_ALREADY_EXISTS,
    USER_ALREADY_HAS_CONTRACT,
    USER_IS_ADMIN_OR_SUPERADMIN,
    USER_FREE_EXPIRED,
    
    SSO_ALG_KID_JOSEHEADER_NOT_MATCHING,
    SSO_EXPIRATION_TIME_ID_TOKEN_INVALID,
    SSO_ID_TOKEN_INCORRECT_DATA,
    SSO_ID_TOKEN_NOT_FOUND,
    SSO_ID_TOKEN_MALFORMED,
    SSO_JSON_PROCESSING_PAYLOAD_JOSEHEADER,
    SSO_CANT_GET_ALG_KID,
    SSO_CANT_GET_ACCESS_TOKEN,
    SSO_CANT_GET_USER_INFO,
    SSO_EMAIL_NOT_FOUND_USERINFO,
    SSO_ISSUER_NOT_MATCHING,
    
    ACCOUNT_WITH_SAME_DOMAIN_ALREADY_EXIST,
    ERROR_DISPOSABLE_EMAIL

}
