package com.example.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import com.example.hello.ui.theme.HelloTheme
import kotlinx.coroutines.launch

/**
 * 主Activity类，应用程序的入口点
 * 使用Jetpack Compose实现UI界面
 */
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloTheme {
                MainScreen()
            }
        }
    }
}

/**
 * 主屏幕组件，包含导航抽屉、顶部应用栏和主要内容区域
 * @param drawerState 导航抽屉的状态控制器
 * @param scope 协程作用域，用于处理抽屉动画
 * @param titleText 顶部应用栏标题文本，支持动画切换
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var titleText by remember { mutableStateOf("Hello!") }

    ModalNavigationDrawer(
        drawerState = drawerState,  // 控制抽屉的状态（打开/关闭）
        gesturesEnabled = true,      // 启用手势控制
        drawerContent = { DrawerContent() }  // 抽屉内容组件
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),  // 加载背景图片资源
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds  // 图片填充整个屏幕
            )
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,  // 设置透明背景
                topBar = {
                    TopAppBar(
                        title = {
                            AnimatedContent(
                                targetState = titleText,  // 标题文本状态
                                transitionSpec = {
                                    fadeIn(animationSpec = tween(300)) togetherWith  // 淡入动画
                                    fadeOut(animationSpec = tween(300))  // 淡出动画
                                },
                                label = "Title Animation"
                            ) { text ->
                                Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary  // 顶部栏背景色
                        ),
                        actions = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()  // 打开抽屉菜单
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = MaterialTheme.colorScheme.onPrimary  // 菜单图标颜色
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Greeting(
                    modifier = Modifier.padding(innerPadding),
                    onTitleChange = { titleText = if (titleText == "Hello!") "我的第一个安卓程序" else "Hello!" }  // 切换标题文本
                )
            }
        }
    }
}

/**
 * 导航抽屉内容组件
 * 目前为空的抽屉面板，可以根据需要添加导航项
 */
@Composable
fun DrawerContent() {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.95f),
        drawerContentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f)
    ) {
        // 侧边栏内容目前为空
    }
}

/**
 * 主要内容区域组件
 * @param modifier Modifier修饰符
 * @param onTitleChange 标题切换回调函数
 */
@Composable
fun Greeting(modifier: Modifier = Modifier, onTitleChange: () -> Unit) {
    var count by remember { mutableIntStateOf(0) }  // 计数器状态变量
    
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,  // 水平居中对齐
        verticalArrangement = Arrangement.Center  // 垂直居中对齐
    ) {
        Surface(
            modifier = Modifier.wrapContentHeight(),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),  // 半透明背景
            shape = MaterialTheme.shapes.medium  // 圆角形状
        ) {
            Text(
                text = "HatsuChuwu",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold  // 粗体文本
            )
        }
        
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,  // 垂直居中对齐
            horizontalArrangement = Arrangement.Center  // 水平居中对齐
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),  // 半透明背景
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.height(48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center  // 内容居中对齐
                ) {
                    Text(
                        text = "点击次数: $count",  // 显示计数值
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(8.dp))  // 水平间距
            
            Surface(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),  // 半透明背景
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.size(48.dp)  // 正方形按钮
            ) {
                IconButton(
                    onClick = { count = 0 },  // 重置计数器
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "重置计数",
                        tint = MaterialTheme.colorScheme.secondary  // 图标颜色
                    )
                }
            }
        }
        
        Button(
            onClick = { 
                count++  // 增加计数
                onTitleChange()  // 触发标题切换
            },
            modifier = Modifier.padding(top = 16.dp),
            shape = MaterialTheme.shapes.medium  // 圆角按钮
        ) {
            Text("点击我")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloTheme {
        Greeting(onTitleChange = {})
    }
}