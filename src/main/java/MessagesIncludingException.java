/* * Copyright (c) 2008-2012 *  Rough Diamond Co., Ltd.              -- http://www.rough-diamond.co.jp/ *  Information Systems Institute, Ltd.  -- http://www.isken.co.jp/ *  All rights reserved. *//** * Messagesオブジェクトを内包するException */public class MessagesIncludingException extends Exception {    private static final long serialVersionUID = 1L;    private final Messages msg;        public MessagesIncludingException(Messages msg) {        super();        this.msg = msg;    }    public MessagesIncludingException(String message, Throwable cause, Messages msg) {        super(message, cause);        this.msg = msg;    }    public MessagesIncludingException(String message, Messages msg) {        super(message);        this.msg = msg;    }    public MessagesIncludingException(Throwable cause, Messages msg) {        super(cause);        this.msg = msg;    }        public Messages getMessages() {        return msg;    }    @Override    public String getMessage() {        String ret = super.getMessage();        if(ret == null || ret.isEmpty()) {            return getMessages().toString();        } else {            return ret;        }    }}