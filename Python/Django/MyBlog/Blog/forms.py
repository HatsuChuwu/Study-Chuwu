from django import forms
from .models import Post

class PostForm(forms.ModelForm):
    class Meta:
        model = Post
        fields = ['title', 'content']
        widgets = {
            'title': forms.TextInput(attrs={
                'class': 'input',
                'placeholder': '请输入文章标题',
                'required': True  # HTML5 前端验证
            }),
            'content': forms.Textarea(attrs={
                'class': 'textarea',
                'placeholder': '请输入文章内容',
                'required': True  # HTML5 前端验证
            }),
        }

    def clean_title(self):
        title = self.cleaned_data.get('title')
        if not title:
            raise forms.ValidationError("标题不能为空！")
        if len(title) > 200:
            raise forms.ValidationError("标题长度不能超过 200 个字符！")
        return title

    def clean_content(self):
        content = self.cleaned_data.get('content')
        if not content:
            raise forms.ValidationError("内容不能为空！")
        if len(content) < 10:
            raise forms.ValidationError("内容长度不能少于 10 个字符！")
        return content
