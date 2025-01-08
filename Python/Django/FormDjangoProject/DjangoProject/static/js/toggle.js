// 获取切换按钮和 body 元素
const toggleButton = document.getElementById('toggle-button');
const body = document.body;

// 从 localStorage 中读取用户偏好
const savedSlideshow = localStorage.getItem('slideshow');
if (savedSlideshow === 'enabled') {
    body.classList.add('slideshow');
    toggleButton.classList.add('active');
}

// 切换按钮点击事件
toggleButton.addEventListener('click', () => {
    // 切换轮播状态
    body.classList.toggle('slideshow');

    // 切换按钮状态
    toggleButton.classList.toggle('active');

    // 更新 localStorage
    if (body.classList.contains('slideshow')) {
        localStorage.setItem('slideshow', 'enabled');
    } else {
        localStorage.setItem('slideshow', 'disabled');
    }
});
