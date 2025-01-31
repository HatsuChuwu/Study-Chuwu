<template>
  <section id="hero">
    <div class="section-wrap">
      <header>
        <a class="head-logo"><span>HatsuneMiku</span></a>

        <nav>
          <a class="nav-items">Home</a>
          <a class="nav-items">About</a>
          <a class="nav-items">Projects</a>
          <a class="nav-items">Blog</a>
          <a class="nav-items">Contact</a>
        </nav>

        <div class="head-cta">
          <button class="header-cta-btn">Login</button>
          <button class="header-cta-btn">Be a Partner</button>
        </div>
      </header>

      <div class="hero-content">
        <div class="left">
          <h1 class="main-text">Love <span> HatsuneMiku, </span> Save <span> a Life!</span></h1>

          <h2 class="sub-text">加入我们，一起创造一个更美好的未来，Miku！</h2>

          <div class="hero-cta">
            <button class="hero-cta-btn btn1">这里有一个Miku</button>
            <button class="hero-cta-btn btn2">Learn more</button>
          </div>
        </div>

        <div class="right">
          <img src="/images/1.png" alt="大Miku">
        </div>
      </div>

      <div class="social">
        <i class="fa-brands fa-facebook-f"></i>
        <i class="fa-brands fa-twitter"></i>
        <i class="fa-brands fa-linkedin-in"></i>
        <i class="fa-brands fa-youtube"></i>
      </div>

    </div>
  </section>

  <section id="about">
    <div class="section-wrap">
      <h3 class="section-title"><span>About </span>us</h3>
      <p class="p1">
        Miku在改善空气质量、节约用水、支持野生动物和减缓全球变暖的影响方面发挥着重要作用。
      </p>

      <p class="p2">
        在绿色未来项目中，我们组织爱Miku活动，与当地社区合作，并教育人们了解可持续发展的重要性。我们可以共同建设一个自然繁荣的未来，我们今天采取的每一个行动都能创造一个更美好的明天。无论是通过爱Miku、传播意识还是支持我们的努力，您都可以做出有意义的改变。加入我们，共同保护我们的星球，为子孙后代创造一个更光明的未来。
      </p>
      <button class="section-btn">Learn more</button>
      <img src="/images/half.png" />
    </div>
  </section>

  <section id="project">
    <div class="section-wrap">
      <h3 class="section-title"><span>Our </span>Miku</h3>

      <div class="project-grid">

        <div class="card crd-1">
          <img src="/images/project1.jpg">
          <h4 class="card-title">Project 1</h4>
        </div>
        <div class="card crd-2">
          <img src="/images/project2.jpg">
          <h4 class="card-title">Project 2</h4>
        </div>
        <div class="card crd-3">
          <img src="/images/project3.jpg">
          <h4 class="card-title">Project 3</h4>
        </div>
        <div class="card crd-4">
          <img src="/images/project4.jpg">
          <h4 class="card-title">Project 4</h4>
        </div>

      </div>

      <button class="section-btn">See all</button>
    </div>
  </section>

  <section id="contact">
    <div class="section-wrap">
      <div class="contact-grid">
        <div class="right">
          <img src="/images/2.png" alt="">
        </div>

        <div class="left">
          <h3 class="section-title"><span>get in </span> touch</h3>

          <p>Let’s create something amazing together. Send us a message now!</p>

          <form>
            <input class="in-1" type="text" placeholder="Your name" spellcheck="false">

            <input class="in-2" type="text" placeholder="Your email" spellcheck="false">

            <textarea placeholder="Your message" spellcheck="false"></textarea>

            <button>Send</button>

          </form>
        </div>
      </div>
    </div>
  </section>

  <section id="footer">
    <div class="section-wrap">
      <div class="social">
        <i class="fa-brands fa-facebook-f"></i>
        <i class="fa-brands fa-twitter"></i>
        <i class="fa-brands fa-linkedin-in"></i>
        <i class="fa-brands fa-youtube"></i>
      </div>

      <p class="copyright">© CodeGenWeb 2024</p>
    </div>
  </section>
</template>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');
@import url('../assets/css/styles.css');
@import url('../assets/css/animation.css');
@import url('../assets/css/responsive-css.css');
</style>
