set time zone 'Europe/Moscow';

insert into chat.user (login, password)
values  ('Андрей', 'andrey'),
        ('Наташа', 'natasha'),
        ('Pon', 'пон'),
        ('Алексей', 'alex1999'),
        ('Олег', 'yaoleg');

insert into chat.room (chat_name, chat_owner)
values  ('general', 4),
        ('random', 5),
        ('hvayon', 1),
        ('котики', 2),
        ('bocal', 3);

insert into chat.message (room_id, author, message, time)
values  (1, 1, 'Всем привет', '10-12-2022 16:00:00'),
        (1, 2, 'Привет', '10-12-2022 16:01:00'),
        (1, 4, 'Кто ты?', '10-12-2022 16:02:00'),
        (2, 4, 'Кто знает, что задали?', '10-12-2022 16:02:30'),
        (2, 2, 'Я не знаю', '10-12-2022 16:03:00'),
        (2, 5, 'Что здесь происходит?', '10-12-2022 16:03:10');