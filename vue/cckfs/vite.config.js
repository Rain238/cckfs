import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    //设置反向代理，跨域
    server: {
        // host: '0.0.0.0',//设置地址：http://localhost
        port: 4500, // 设置服务启动端口号
        // open: true, // 设置服务启动时是否自动打开浏览器
        cors: true, // 允许跨域
        proxy: {
            '/api': {
                target: 'http://localhost:5170/',//代理的地址
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')//这里的/需要转义
            }
        }
    },
})

