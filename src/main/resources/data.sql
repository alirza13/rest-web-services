insert into user_details(id, birth_date, name)
values(1000, current_date(), 'Ali' );

insert into user_details(id, birth_date, name)
values(1001, current_date(), 'Veli' );

insert into user_details(id, birth_date, name)
values(1002, current_date(), 'Ahmet' );

insert into user_details(id, birth_date, name)
values(1003, current_date(), 'Mehmet' );

insert into post(id, description, user_id)
values(1000, 'Hello', 1001);

insert into post(id, description, user_id)
values(1001, 'Goodbye', 1000);

insert into post(id, description, user_id)
values(1002, 'Nasilsin', 1000);

insert into post(id, description, user_id)
values(1003, 'Iyi misin', 1003);


