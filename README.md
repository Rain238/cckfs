# 服务器Nginx添加以下配置
```text
数据库表在Sql文件夹下
默认账号：user
默认密码：123456
```
```config
server {    
    listen 80;# 端口
    server_name cckfs.fairiy.com;
    location / {
        # proxy_pass http://localhost:5170/;
        # proxy_set_header Host $host;
        # proxy_set_header X-Real-IP $remote_addr;
        root /www/wwwroot/cckfs/dist;
        index index.html;
        try_files $uri $uri/ /index.html;
        error_page 405 =200 @405;  #405页面处理
    }
    location /api/ {
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header REMOTE-HOST $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://localhost:5170/; # 这里的8080是后台端口
    }
    #加入下面代码
    location @405 {
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;  
        #ip为后端服务地址
        proxy_pass http://localhost:5170$request_uri ;
    }
}
```