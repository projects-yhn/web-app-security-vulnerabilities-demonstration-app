INSERT INTO xss_stored_posts(creation_ts, title, content, user_id, user_profile_pictire, user_names)
VALUES (NOW()::date, 'Invest money', 'To invest money you should give more then one percent of yourself. Every year millions of people go to USA to accomplish their dream.', 1, '/image1', 'Stefan Karaivanov'),
       (NOW()::date, 'Eco cars', 'Ecologic crises comes. We should do something to prevent it. .', 2, '/image/2', 'Tom Jeahrein'),
       (NOW()::date, 'The millionaire game', 'How to be a milliner. The game which gives a lot of money to gamers.', 3, '/image/3','Mihail Kanchev');

INSERT INTO xss_stored_comments(creation_ts, user_id, user_profile_pictire, user_names, content, post_id)
VALUES (NOW()::date, 5, '/image/5', 'Strahil Carlson', 'This post is not useful.', (SELECT id from xss_stored_posts where title = 'Invest money')),
       (NOW()::date, 6, '/image/6', 'Maria G', 'Not bad.', (SELECT id from xss_stored_posts where title = 'Invest money')),
       (NOW()::date, 7, '/image/7', 'John Marietta', 'I agree.', (SELECT id from xss_stored_posts where title = 'Eco cars')),
       (NOW()::date, 6, '/image/6', 'Maria G', 'I not agree', (SELECT id from xss_stored_posts where title = 'Eco cars')),
       (NOW()::date, 8, '/image/8', 'Arnold S', 'I won', (SELECT id from xss_stored_posts where title = 'The millionaire game'));
