package com.Hackathon6.android.bean;

import java.util.List;

/**
 * Created by simple on 23/10/2017.
 */

public class MayWhatBean {


    /**
     * errorDetails : {"errorCode":0,"message":"string"}
     * successful : true
     * value : [{"answer":"string","heavy":0,"id":0,"keywords":"string","title":"string","type":"PIC"}]
     */

    private ErrorDetailsBean errorDetails;
    private boolean successful;
    private List<ValueBean> value;

    public ErrorDetailsBean getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(ErrorDetailsBean errorDetails) {
        this.errorDetails = errorDetails;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ErrorDetailsBean {
        /**
         * errorCode : 0
         * message : string
         */

        private int errorCode;
        private String message;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ValueBean {
        /**
         * answer : string
         * heavy : 0
         * id : 0
         * keywords : string
         * title : string
         * type : PIC
         */

        private String answer;
        private int heavy;
        private int id;
        private String keywords;
        private String title;
        private String type;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getHeavy() {
            return heavy;
        }

        public void setHeavy(int heavy) {
            this.heavy = heavy;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
