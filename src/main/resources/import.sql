-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into `state` (`name`, acronym) values( 'Tocantins', 'TO');
insert into `state` (`name`, acronym) values( 'Goiás', 'GO');
insert into `state` (`name`, acronym) values( 'São Paulo', 'SP');
insert into `state` (`name`, acronym) values( 'Rio de Janeiro', 'RJ');
insert into `state` (`name`, acronym) values( 'Pará', 'PA');

insert into city (`name`, id_state) values( 'Palmas', 1);
insert into city (`name`, id_state) values( 'Paraíso', 1);
insert into city (`name`, id_state) values( 'Gurupi', 1);
insert into city (`name`, id_state) values( 'Goiânia', 2);
insert into city (`name`, id_state) values( 'Anápolis', 2);

insert into users (`name`, login, `password`, `profile`) values('Elon Musk', 'musk', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into users (`name`, login, `password`, `profile`) values('Bill Gates', 'gates', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);

insert into phone (areaCode, `number`) values('63', '9999-9999');
insert into phone (areaCode, `number`) values('62', '8888-8888');
insert into phone (areaCode, `number`) values('61', '7777-7777');
insert into phone (areaCode, `number`) values('55', '6666-6666');

insert into user_phone (id_user, id_phone) values(1, 1);
insert into user_phone (id_user, id_phone) values(1, 2);
insert into user_phone (id_user, id_phone) values(2, 3);
insert into user_phone (id_user, id_phone) values(2, 4);

insert into address_table(`name`, postalCode, `address`, complement, id_city) values('Casa', '0000000', 'Avenida 5 casa 5', 'Em frente ao bar do zé', 1);
insert into address_table(`name`, postalCode, `address`, complement, id_city) values('Trabalho', '11111111', 'Rua almeida, predio 12', 'sede tal', 1);
insert into address_table(`name`, postalCode, `address`, complement, id_city) values('Casa', '55555440', 'Avenida santa hora ap 22b', 'condominio tal', 1);

insert into mangas(`name`, `description`, price, inventory, numPages, volume, id_author) values('Manga teste 1', 'Descrição foda', 60, 100, 200, 10, 1);
insert into mangas(`name`, `description`, price, inventory, numPages, volume, id_author) values('Manga teste 2', 'Descrição foda', 60, 50, 300, 18, 1);
insert into mangas(`name`, `description`, price, inventory, numPages, volume, id_author) values('Manga teste 3', 'Descrição foda', 60, 50, 300, 20, 1);
insert into mangas(`name`, `description`, price, inventory, numPages, volume, id_author) values('Manga teste 4', 'Descrição foda', 60, 50, 300, 32, 1);
insert into mangas(`name`, `description`, price, inventory, numPages, volume, id_author) values('Manga teste 5', 'Descrição foda', 60, 50, 300, 31, 1);

insert into genre_table (`name`, `description`) values('Hentai', 'SSEEEEGGGGGs UOOHHH');
insert into genre_table (`name`, `description`) values('Aventura', 'Aventura pika loca');
insert into genre_table (`name`, `description`) values('Ação', 'Ação ação');
insert into genre_table (`name`, `description`) values('Isekai', 'Aventuras de outro mundo');

insert into collections (`name`, `description`) values('Blue Archive', 'CUUNNNNNYYYY UOOHHH');
insert into collections (`name`, `description`) values('Dragon Bolas', 'Aventura pika loca');
insert into collections (`name`, `description`) values('Mergulhadores do inferno', 'Cima Direita Baixo Baixo Baixo');
insert into collections (`name`, `description`) values('Fisting of the north', 'Sei lá oq');

insert into publisher (`name`) values('Panini');
insert into publisher (`name`) values('Jbc');
insert into publisher (`name`) values('New pop');
insert into publisher (`name`) values('Square Enix');

insert into author_table (`name`) values('Frederic Vertman');
insert into author_table (`name`) values('Jonathas Antones');