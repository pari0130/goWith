package com.jd.gowith.common.exception.posts;

public class PostsNotFoundException extends RuntimeException {
    public PostsNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public PostsNotFoundException(String msg) {
        super(msg);
    }

    public PostsNotFoundException() {
        super();
    }
}
