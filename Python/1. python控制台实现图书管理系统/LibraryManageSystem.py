class Book:
    def __init__(self, title, author, year, isbn, category=""):
        self.title = title
        self.author = author
        self.year = year
        self.isbn = isbn
        self.category = category  # 可选的书籍分类
        self.is_borrowed = False  # 默认未借出
        self.borrower = None
        self.borrow_date = None
        self.due_date = None

class User:
    def __init__(self, username, password):
        self.username = username
        self.password = password

class Library:
    def __init__(self):
        self.books = []
        self.users = []  # 存储用户信息
        self.load_data_from_file()
        self.load_users_from_file()

    def add_book(self, book):
        self.books.append(book)
        self.save_books_to_file()

    def search_books(self, keyword):
        return [book for book in self.books if
                keyword.lower() in book.title.lower() or
                keyword.lower() in book.author.lower()]

    def remove_book(self, isbn):
        for book in self.books:
            if book.isbn == isbn:
                self.books.remove(book)
                self.save_books_to_file()
                return True
        return False

    def display_books(self):
        if not self.books:
            print("当前没有任何图书。")
        for book in self.books:
            print(f"标题: {book.title}, 作者: {book.author}, 出版年份: {book.year}, ISBN: {book.isbn}, 分类: {book.category}")
            if book.is_borrowed:
                print(f"状态: 已借出（借阅者: {book.borrower}, 借阅日期: {book.borrow_date}, 归还日期: {book.due_date}）")
            else:
                print("状态: 可借")

    def save_books_to_file(self):
        with open("books.txt", "w", encoding="utf-8") as f:
            for book in self.books:
                f.write(f"Title: {book.title}, Author: {book.author}, Year: {book.year}, ISBN: {book.isbn}, Category: {book.category}, Borrowed: {book.is_borrowed}, Borrower: {book.borrower if book.borrower else 'None'}, BorrowDate: {book.borrow_date if book.borrow_date else 'None'}, DueDate: {book.due_date if book.due_date else 'None'}\n")

    def load_data_from_file(self):
        try:
            with open("books.txt", "r", encoding="utf-8") as f:
                for line in f:
                    # 使用正则表达式分割带标签的属性
                    import re
                    attributes = re.findall(r'(\w+): ([^,]+)', line.strip())

                    # 将属性转换为字典
                    book_data = {attr[0]: attr[1] for attr in attributes}

                    # 创建 Book 对象
                    book = Book(
                        title=book_data['Title'],
                        author=book_data['Author'],
                        year=int(book_data['Year']),  # 转换为整数
                        isbn=book_data['ISBN'],
                        category=book_data['Category']
                    )

                    # 设置借阅相关属性
                    book.is_borrowed = (book_data['Borrowed'] == "True")
                    book.borrower = book_data['Borrower'] if book_data['Borrower'] != "None" else None
                    book.borrow_date = book_data['BorrowDate'] if book_data['BorrowDate'] != "None" else None
                    book.due_date = book_data['DueDate'] if book_data['DueDate'] != "None" else None

                    self.books.append(book)
        except FileNotFoundError:
            pass

    def add_user(self, user):
        self.users.append(user)
        self.save_users_to_file()

    def authenticate_user(self, username, password):
        for user in self.users:
            if user.username == username and user.password == password:
                return True
        return False

    def save_users_to_file(self):
        with open("users.txt", "w", encoding="utf-8") as f:
            for user in self.users:
                f.write(f"{user.username},{user.password}\n")

    def load_users_from_file(self):
        try:
            with open("users.txt", "r", encoding="utf-8") as f:
                for line in f:
                    (username, password) = line.strip().split(",")
                    self.users.append(User(username, password))
        except FileNotFoundError:
            pass

def main():
    library = Library()
    logged_in = False  # 用于跟踪用户是否已登录

    while True:
        print("\n=== 图书管理系统 ===")
        print("1. 添加图书")
        print("2. 搜索图书")
        print("3. 删除图书")
        print("4. 显示所有图书")
        print("5. 退出系统")
        if not logged_in:
            print("6. 用户登录")
            print("7. 注册用户")
        else:
            print("6. 注销")

        choice = input("请输入操作选项: ")

        if choice == "1" and logged_in:
            title = input("请输入图书标题: ")
            author = input("请输入图书作者: ")
            year = int(input("请输入出版年份: "))  # 转换为整数
            isbn = input("请输入ISBN: ")
            category = input("请输入分类（可选）: ")
            book = Book(title, author, year, isbn, category)
            library.add_book(book)
            print("图书添加成功！")
        elif choice == "2":  # 搜索图书不需要登录
            keyword = input("请输入搜索关键词: ")
            results = library.search_books(keyword)
            if results:
                print("搜索结果如下:")
                for book in results:
                    print(f"标题: {book.title}, 作者: {book.author}, ISBN: {book.isbn}")
            else:
                print("未找到匹配的图书。")
        elif choice == "3" and logged_in:
            isbn = input("请输入要删除的图书ISBN: ")
            if library.remove_book(isbn):
                print("图书删除成功！")
            else:
                print("未找到匹配的图书。")
        elif choice == "4":  # 显示所有图书不需要登录
            print("\n所有图书信息如下:")
            library.display_books()
        elif choice == "5":
            print("系统已退出。")
            break
        elif choice == "6" and not logged_in:  # 用户登录
            username = input("请输入用户名: ")
            password = input("请输入密码: ")
            if library.authenticate_user(username, password):
                print("登录成功！")
                logged_in = True
            else:
                print("用户名或密码错误，请重新输入。")
        elif choice == "6" and logged_in:  # 注销
            print("已注销。")
            logged_in = False
        elif choice == "7" and not logged_in:  # 注册用户
            username = input("请输入新用户名: ")
            password = input("请输入密码: ")
            if username and password:
                library.add_user(User(username, password))
                print("注册成功！")
            else:
                print("用户名和密码不能为空。")
        else:
            print("无效选项，请重新输入。")

if __name__ == "__main__":
    main()