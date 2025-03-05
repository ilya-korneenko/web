document.addEventListener('DOMContentLoaded', function () {
    const createPostForm = document.getElementById('createPostForm');
    const postsList = document.getElementById('postsList');


    // Загрузка постов
    async function loadPosts() {
        try {
            const response = await fetch('/posts');
            if (!response.ok) {
                throw new Error('Ошибка при загрузке постов');
            }
            const posts = await response.json();
            postsList.innerHTML = '';
            posts.forEach(post => {
                const li = renderPost(post);
                postsList.appendChild(li);
                loadComments(post.id);
                updateLikeCount(post.id);
            });
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    // Отрисовка поста
    function renderPost(post) {
        const li = document.createElement('li');
        li.dataset.postId = post.id;
        li.innerHTML = `
            <h3>${post.title}</h3>
            <p>${post.content}</p>
            <small>Автор: ${post.author.id}</small>
            <div class="post-actions">
                <button class="like-btn">❤️ <span class="like-count">0</span></button>
                <button class="edit-btn">✏️</button>
                <button class="delete-btn">🗑️</button>
            </div>
            <div class="comments-section" data-post-id="${post.id}">
                <h4>Комментарии</h4>
                <ul class="comments-list"></ul>
                <form class="comment-form">
                    <input type="text" placeholder="Ваш комментарий" required>
                    <button type="submit">Отправить</button>
                </form>
            </div>
        `;

        // Обработчики кнопок
        li.querySelector('.delete-btn').addEventListener('click', () => deletePost(post.id));
        li.querySelector('.edit-btn').addEventListener('click', () => editPost(post.id));
        li.querySelector('.like-btn').addEventListener('click', () => handleLike(post.id));

        // Обработчик комментариев
        li.querySelector('.comment-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const input = e.target.querySelector('input');
            addComment(post.id, input.value);
            input.value = '';
        });

        return li;
    }

    // Создание поста
    createPostForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const title = document.getElementById('postTitle').value;
        const content = document.getElementById('postContent').value;
        const authorId = document.getElementById('postAuthorId').value;

        try {
            const response = await fetch('/posts', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title, content, authorId })
            });

            if (response.ok) {
                alert('Пост создан!');
                createPostForm.reset();
                loadPosts();
            } else {
                alert('Ошибка при создании поста');
            }
        } catch (error) {
            console.error('Ошибка:', error);
        }
    });

    // Удаление поста
    async function deletePost(postId) {
        if (!confirm('Удалить пост?')) return;

        try {
            const response = await fetch(`/posts/${postId}`, { method: 'DELETE' });
            if (response.ok) {
                document.querySelector(`li[data-post-id="${postId}"]`).remove();
            }
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    // Редактирование поста
    async function editPost(postId) {
        const title = prompt('Новый заголовок:');
        const content = prompt('Новое содержимое:');

        if (title && content) {
            try {
                console.log("Отправка запроса на обновление поста:", { id: postId, title, content });

                const postResponse = await fetch(`/posts/${postId}`);
                if (!postResponse.ok) {
                    console.error("Ошибка при получении поста:", await postResponse.text());
                    return;
                }
                const currentPost = await postResponse.json();

                // Проверяем, есть ли у поста автор
                const authorId = currentPost.author ? currentPost.author.id : null;
                if (!authorId) {
                    console.error("Ошибка: у поста нет автора!");
                    return;
                }

                const updateResponse = await fetch(`/posts/${postId}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        title,
                        content,
                        authorId
                    })
                });

                if (!updateResponse.ok) {
                    console.error("Ошибка при обновлении поста:", await updateResponse.text());
                    return;
                }

                const updatedPost = await updateResponse.json();
                updatePostInDOM(updatedPost);

            } catch (error) {
                console.error('Ошибка:', error);
            }
        }
    }


    // Обновление поста в DOM
    function updatePostInDOM(post) {
        const postElement = document.querySelector(`li[data-post-id="${post.id}"]`);
        if (postElement) {
            postElement.querySelector('h3').textContent = post.title;
            postElement.querySelector('p').textContent = post.content;
        }
    }

    // Лайки
    async function handleLike(postId) {
        try {
            const response = await fetch('/likes', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    postId: postId,
                    userId: 1 // Временное значение до реализации авторизации
                })
            });

            if (response.ok) {
                const newCount = await response.json();
                updateLikeCount(postId, newCount);
            }
        } catch (error) {
            console.error('Ошибка при отправке лайка:', error);
        }
    }

    // Обновление счетчика лайков
    async function updateLikeCount(postId, count) {
        const likeElement = document.querySelector(`li[data-post-id="${postId}"] .like-count`);
        if (likeElement) {
            likeElement.textContent = count;
        }
    }

    // Комментарии
    async function addComment(postId, text) {
        try {
            console.log("Отправляем JSON:", JSON.stringify({ text, postId, authorId: 1 }));

            const response = await fetch(`/posts/${postId}/comments`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                //console.log("Отправляем JSON:", JSON.stringify({ text, authorId: 1 }));
                body: JSON.stringify({text, postId, authorId: 1 })
            });
const errorText = await response.text();
console.log("Ответ сервера:", errorText);
            if (response.ok) {
                loadComments(postId);
            }
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    async function loadComments(postId) {
        try {
            const response = await fetch(`/posts/${postId}/comments`);
            const comments = await response.json();
            const list = document.querySelector(`.comments-section[data-post-id="${postId}"] .comments-list`);
            list.innerHTML = '';
            comments.forEach(comment => {
                const li = document.createElement('li');
                li.textContent = comment.text;
                list.appendChild(li);
            });
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    // Инициализация
    loadPosts();
});