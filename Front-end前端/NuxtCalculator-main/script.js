// DOM 元素
const display = document.getElementById('display');
const themeToggle = document.getElementById('theme-toggle');
const body = document.body;

// 音效反馈
const playButtonSound = () => {
    const audio = new Audio('data:audio/wav;base64,UklGRhwEAABXQVZFZm10IBAAAAABAAEARKwAAIhYAQACABAAZGF0YfgDAAD/f/9//3//f/9//3//f/9//3//f/9//3//f/9//3//f/9//3//f/9//3//f/9//38AAP4A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gAAAP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4AAAD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQABAAEAAQABAAEAAQABAAEAAQABAAEAAQABAAEAAQABAAEAAQABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//');
    audio.volume = 0.2;
    audio.play().catch(() => {}); // 忽略自动播放限制错误
};

// 格式化显示数字
const formatNumber = (num) => {
    if (typeof num !== 'number') return num;
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

// 将值追加到显示框中
function appendToDisplay(value) {
    playButtonSound();
    display.value += value;
}

// 清除显示框中的内容
function clearDisplay() {
    playButtonSound();
    display.value = '';
}

// 删除显示框中的最后一个字符
function deleteLast() {
    playButtonSound();
    display.value = display.value.slice(0, -1);
}

// 计算显示框中的表达式并显示结果
function calculate() {
    playButtonSound();
    try {
        const result = eval(display.value);
        // 检查结果是否为有限数
        if (!isFinite(result)) {
            throw new Error('计算结果无效');
        }
        // 格式化结果
        display.value = typeof result === 'number' ? 
            formatNumber(parseFloat(result.toFixed(8))) : 
            result;
    } catch (e) {
        display.value = '错误';
        console.error('计算错误:', e.message);
    }
}

// 主题切换相关
const setupTheme = () => {
    // 从本地存储加载主题
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
        body.classList.add('dark-mode');
        themeToggle.classList.add('dark-mode');
    }

    // 主题切换事件监听
    themeToggle.addEventListener('click', () => {
        playButtonSound();
        body.classList.toggle('dark-mode');
        themeToggle.classList.toggle('dark-mode');

        // 保存主题首选项
        localStorage.setItem('theme', 
            body.classList.contains('dark-mode') ? 'dark' : 'light'
        );
    });
};

// 按钮大小调整
const resizeButtons = () => {
    const buttons = document.querySelectorAll('.buttons button');
    const buttonContainer = document.querySelector('.buttons');
    const containerWidth = buttonContainer.offsetWidth;
    const buttonWidth = (containerWidth - 36) / 4; // 36 = gap * 3

    buttons.forEach(button => {
        button.style.width = buttonWidth + 'px';
    });
};

// 键盘事件监听
const setupKeyboardInput = () => {
    document.addEventListener('keydown', (e) => {
        const key = e.key;
        if (/[\d\+\-\*\/\(\)\.=]/.test(key)) {
            e.preventDefault();
            if (key === '=') {
                calculate();
            } else {
                appendToDisplay(key);
            }
        } else if (key === 'Enter') {
            e.preventDefault();
            calculate();
        } else if (key === 'Backspace') {
            e.preventDefault();
            deleteLast();
        } else if (key === 'Escape') {
            e.preventDefault();
            clearDisplay();
        }
    });
};

// 页面加载完成后初始化
window.addEventListener('load', () => {
    setupTheme();
    resizeButtons();
    setupKeyboardInput();
});

// 窗口大小改变时重新调整按钮大小
window.addEventListener('resize', resizeButtons);