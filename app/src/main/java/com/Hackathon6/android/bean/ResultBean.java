package com.Hackathon6.android.bean;

/**
 * Created by simple on 23/10/2017.
 */

public class ResultBean {


    /**
     * successful : true
     * value : {"type":"TEXT","body":"Check-out time is noon"}
     * errorDetails : null
     */

    private boolean successful;
    private ValueBean value;
    private Object errorDetails;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public Object getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Object errorDetails) {
        this.errorDetails = errorDetails;
    }

    public static class ValueBean {
        /**
         * type : TEXT
         * body : Check-out time is noon
         */

        private String type;
        private String body;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
