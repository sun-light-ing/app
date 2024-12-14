# 使用 PHP 7.4 和 NGINX 的基础镜像
FROM php:7.4-fpm

RUN sed -i 's|http://deb.debian.org|http://mirrors.aliyun.com|g' /etc/apt/sources.list && \
    apt-get update && \
    apt-get install -y \
        nginx \
        libzip-dev \
        zip && \
    docker-php-ext-install zip pdo pdo_mysql


# 设置工作目录
WORKDIR /var/www/html

# 复制项目文件到容器中
COPY ./tp /var/www/html/

# 设置文件权限
RUN chown -R www-data:www-data /var/www/html

# 暴露 NGINX 和 PHP 的端口
EXPOSE 9000

# 启动 NGINX 和 PHP-FPM
CMD ["php-fpm"]
