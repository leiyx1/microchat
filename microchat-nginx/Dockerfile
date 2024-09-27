# Dockerfile
FROM openresty/openresty:alpine

RUN apk add --no-cache curl perl

RUN opm get SkyLothar/lua-resty-jwt

# Copy your NGINX configuration file
COPY nginx.conf /usr/local/openresty/nginx/conf/nginx.conf