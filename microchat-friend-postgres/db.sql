\c microchat-friend;

CREATE TABLE public.friendship (
    user_id UUID NOT NULL,
    friend_id UUID NOT NULL,
    PRIMARY KEY (user_id, friend_id)
);

CREATE TABLE public.block (
    user_id UUID NOT NULL,
    blocked_user_id UUID NOT NULL,
    PRIMARY KEY (user_id, blocked_user_id)
);

CREATE TYPE friend_request_status as enum ('PENDING', 'DECLINED', 'APPROVED');

CREATE TABLE public.friend_request (
    id UUID NOT NULL,
    sender_id UUID NOT NULL,
    receiver_id UUID NOT NULL,
    created_date TIMESTAMP,
    status friend_request_status
)