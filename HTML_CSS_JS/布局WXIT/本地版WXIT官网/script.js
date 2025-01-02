// 轮播图图片数组
const images = [
    'images/runimage1.jpg',
    'images/runimage2.jpg',
    'images/runimage3.jpg'
];

// 获取轮播图元素
const carousel = document.querySelector('.carousel');

let currentIndex = 0; // 当前显示的图片索引

// 切换轮播图图片的函数
function changeCarouselImage() {
    // 更新轮播图的背景图片
    carousel.style.backgroundImage = `url('${images[currentIndex]}')`;

    // 更新索引，循环播放
    currentIndex = (currentIndex + 1) % images.length;
}

// 设置轮播图切换间隔（单位：毫秒）
const interval = 3000; // 3秒切换一次

// 启动轮播图
setInterval(changeCarouselImage, interval);

// 初始化显示第一张图片
changeCarouselImage();