document.addEventListener("DOMContentLoaded", () => {
    // 使用从HTML模板传递过来的URL
    const images = imageUrls;
    let loadedImages = 0; // 用于跟踪已加载的图片数量
    const preloadedImages = []; // 存储预加载的图片

    // 预加载图片
    images.forEach(src => {
        const img = new Image();
        img.src = src;
        img.onload = () => { // 图片加载完成后的回调函数
            loadedImages++; // 增加已加载的图片数量
            preloadedImages.push(img); // 存储预加载的图片
            if (loadedImages === images.length) {
                console.log("所有背景图片已预加载完成");
                // 初始化轮播图
                startSlideshow();
            }
        };
        img.onerror = () => { // 图片加载失败时的回调函数
            console.error(`图片加载失败: ${src}`); // 输出失败的图片 URL
        };
    });

    // 将预加载的图片存储在全局变量中
    window.preloadedImages = preloadedImages;
});

function startSlideshow() {
    let currentIndex = 0;
    const preloadedImages = window.preloadedImages;

    function updateBackgroundImage() {
        if (preloadedImages && preloadedImages.length > 0) {
            document.body.style.backgroundImage = `url(${preloadedImages[currentIndex].src})`;
            currentIndex = (currentIndex + 1) % preloadedImages.length;
        }
    }

    // 初始化轮播图
    setInterval(updateBackgroundImage, 5000); // 每5秒切换一次背景图片
}
