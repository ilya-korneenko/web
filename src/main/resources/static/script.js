document.addEventListener('DOMContentLoaded', function () {
    const createPostForm = document.getElementById('createPostForm');
    const postsList = document.getElementById('postsList');

    // Функция для загрузки постов
    async function loadPosts() {
        const response = await fetch('/posts');
        const posts = await response.json();
        postsList.innerHTML = ''; // Очищаем список перед добавлением новых постов
        posts.forEach(post => {
            const li = document.createElement('li');
            li.innerHTML = `
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <small>Автор: ${post.author.id}</small>
            `;
            postsList.appendChild(li);
        });
    }

    // Обработка создания поста
    createPostForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const title = document.getElementById('postTitle').value;
        const content = document.getElementById('postContent').value;
        const authorId = document.getElementById('postAuthorId').value;

        const response = await fetch('/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title: title,
                content: content,
                authorId: authorId
            }),
        });

        if (response.ok) {
            alert('Пост успешно создан!');
            createPostForm.reset(); // Очищаем форму
            loadPosts(); // Обновляем список постов
        } else {
            alert('Ошибка при создании поста');
        }
    });

    // Загружаем посты при загрузке страницы
    loadPosts();
});