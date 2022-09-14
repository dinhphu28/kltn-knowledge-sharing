## Branch

### main

1. Không push hay chỉnh sửa trên branch này
2. Khi cần sẽ tạo pull request (không merge ngay)

### dev

> Đây là branch gốc của quá trình dev, khi code tính năng mới sẽ tạo branch mới từ branch này và sau đó sẽ tạo pull request.

Không push trực tiếp lên branch này

### documents

Branch này sẽ dùng cho việc viết tài liệu như báo cáo, design, ppt, tài liệu hướng dẫn, ...

1. Không push trực tiếp lên branch này
2. Khi cần sẽ tạo branch mới rồi tạo pull request

### Other branch

Đối với các branch khác sẽ tạo từ branch dev và documents nói trên, trong quá trình thực hiện sẽ thêm các branch khác phù hợp với nhu cầu.

---

Luôn tạo pull request khi cần gộp branch mình vừa chỉnh sửa để các thành viên cùng nắm, tránh xung đột commit.

Luôn tạo branch name có ý nghĩa tương ứng với công việc đang thực hiện hoặc tính năng, ...

VD: api-articles -> API cho resource articles
fe-articles -> Front-End cho tính năng bài viết
doc-uml-v1.0.0 -> Tài liệu thiết kế UML version 1.0.0

---

## Một số lệnh git thường dùng

1. Xem branch hiện tại và các branch khác trên local

```sh
git branch
```

2. Chuyển sang branch khác

```sh
git checkout branch-name
```

3. Tạo branch mới và chuyển sang branch vừa tạo từ branch hiện tại

```sh
git checkout -b new-branch-name
```

4. Push branch hiện tại lên github (với curr-branch là branch hiện tại)

```sh
git push origin curr-branch
```

5. Pull branch trên github về local (với x-branch là branch muốn pull)

```sh
git pull origin x-branch
```

6. Clone github về local

- Nếu thiết lập sử dụng https (mặc định)

```sh
git clone https://github.com/dinhphu28/kltn-knowledge-sharing.git
```

- Nếu thiết lập sử dụng SSH

```sh
git clone git@github.com:dinhphu28/kltn-knowledge-sharing.git
```

- Nếu thiết lập sử dụng GitHub CLI

```sh
gh repo clone dinhphu28/kltn-knowledge-sharing
```

#### Đối với GitHub CLI

1. Tạo Pull Request từ branch hiện tại

```sh
gh pr create
```