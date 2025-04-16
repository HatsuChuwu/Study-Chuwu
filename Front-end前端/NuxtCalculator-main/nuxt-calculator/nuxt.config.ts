// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  // 基础配置
  ssr: false,
  
  // 页面配置
  app: {
    head: {
      title: '简单计算器',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' }
      ],
      link: [
        {
          rel: 'stylesheet',
          href: 'https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap'
        }
      ]
    }
  },

  // CSS 配置
  css: [
    '@fortawesome/fontawesome-free/css/all.css'
  ]
})