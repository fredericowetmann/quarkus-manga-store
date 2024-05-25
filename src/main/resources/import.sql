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

insert into users (`name`, login, `password`, `profile`) values('Usuario', 'user', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into users (`name`, login, `password`, `profile`) values('Administrador', 'admin', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);
insert into users (`name`, login, `password`, `profile`) values('Frederico Gomes', 'fredericowetmann', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into users (`name`, login, `password`, `profile`) values('João Antonio', 'boris_brother', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);
insert into users (`name`, login, `password`, `profile`) values('João Pedro Gomes', 'JPblack', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into users (`name`, login, `password`, `profile`) values('Alex Recorde de Faltas', 'talimos', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);

insert into phone (areaCode, `number`) values('63', '98434-6823');
insert into phone (areaCode, `number`) values('62', '7834-2386');
insert into phone (areaCode, `number`) values('61', '9245-8923');
insert into phone (areaCode, `number`) values('55', '9285-6396');
insert into phone (areaCode, `number`) values('63', '99263-9654');
insert into phone (areaCode, `number`) values('62', '4689-7384');
insert into phone (areaCode, `number`) values('61', '9263-9543');
insert into phone (areaCode, `number`) values('55', '8535-7615');

insert into user_phone (id_user, id_phone) values(1, 1);
insert into user_phone (id_user, id_phone) values(1, 2);
insert into user_phone (id_user, id_phone) values(2, 3);
insert into user_phone (id_user, id_phone) values(2, 4);
insert into user_phone (id_user, id_phone) values(3, 5);
insert into user_phone (id_user, id_phone) values(3, 6);
insert into user_phone (id_user, id_phone) values(4, 7);
insert into user_phone (id_user, id_phone) values(4, 8);

insert into address_table (`name`, postalCode, `address`, complement, id_city) values('Casa', '0000000', 'Avenida 5 casa 5', 'Em frente ao bar do zé', 1);
insert into address_table (`name`, postalCode, `address`, complement, id_city) values('Trabalho', '11111111', 'Rua almeida, predio 12', 'sede tal', 1);
insert into address_table (`name`, postalCode, `address`, complement, id_city) values('Casa', '55555440', 'Avenida santa hora ap 22b', 'condominio tal', 1);

insert into author (`name`) values('Rifujin na Magonote');
insert into author (`name`) values('Natsume Akatsuki');
insert into author (`name`) values('Yoshiyuki Sadamoto');
insert into author (`name`) values('Yoko Taro');
insert into author (`name`) values('Yuu Kamiya');

insert into collections (`name`, `description`) values('Mushouku Tensei', 'Um japonês NEET de 34 anos, sem nome, é despejado de sua casa após a morte de seus pais. Após alguma auto-introspecção, ele conclui que sua vida foi, em última análise, sem sentido...');
insert into collections (`name`, `description`) values('Konosuba', 'A série conta a história de um garoto que é mandado para um mundo fantasioso após a sua morte...');
insert into collections (`name`, `description`) values('Evangelion', 'Em 2015, quinze anos após um cataclismo global conhecido como Segundo Impacto, o adolescente Shinji Ikari é convocado para a futurística cidade de Tóquio-3 por seu pai distante Gendo Ikari, diretor de uma força paramilitar especial chamada Nerv...');
insert into collections (`name`, `description`) values('No Game No Life', 'Os irmãos Sora e Shiro são inseparáveis, tanto no mundo real quanto no mundo dos jogos. Suas habilidades individuais combinadas fazem deles uma equipe invencível: Sora, com sua intuição e conhecimento surpreendentes...');
insert into collections (`name`, `description`) values('Nier Automata', '2B e 9S são enviados em uma incursão para dentro do território das máquinas inimigas, eliminando as ameaças para a Resistência local. Os dois contam com a ajuda de Anemone e mais tarde Pascal. Os andróides acabam testemunhando o nascimento...');

insert into publisher (`name`) values('Panini');
insert into publisher (`name`) values('Jbc');
insert into publisher (`name`) values('New pop');
insert into publisher (`name`) values('Square Enix');

insert into genre (`name`, `description`) values('Fantasia', 'Poderes mágicos, criaturas não naturais ou outros elementos irreais que não podem ser explicados pela ciência são predominantes e normais para o ambiente em que existem. As histórias de fantasia podem acontecer na Terra ou em outro mundo...');
insert into genre (`name`, `description`) values('Isekai', 'O protagonista é enviado para outro mundo após morrer no mundo real. Um método comum de morte é ser atropelado por um caminhão e morrer, gerando o meme de "Truck-kun", um caminhão que aparece em muitas séries isekai que mata o protagonista...');
insert into genre (`name`, `description`) values('Aventura', 'Seja visando um objetivo específico ou apenas lutando para sobreviver, o personagem principal é lançado em situações ou terras desconhecidas e enfrenta continuamente perigos inesperados. A narrativa das histórias de aventura é sempre sobre como...');
insert into genre (`name`, `description`) values('Comedia', 'Elevar o público com emoções positivas tem prioridade, provocando risos, diversão ou entretenimento geral. Quase sempre, as histórias de comédia são episódicas ou têm finais felizes.');
insert into genre (`name`, `description`) values('Ação', 'Sequências de ação emocionantes têm prioridade e conflitos significativos entre personagens geralmente são resolvidos com o poder físico. Embora o enredo abrangente possa envolver um grupo contra outro, a narrativa em histórias de ação...');
insert into genre (`name`, `description`) values('Drama', 'Histórias baseadas em enredo focadas em personagens realistas que vivenciam a luta humana. Como as histórias dramáticas questionam o que significa ser humano, o conflito e as emoções serão relacionáveis, mesmo que os cenários...');
insert into genre (`name`, `description`) values('Misterio', 'Seja resolvendo um crime ou encontrando uma explicação para uma circunstância intrigante, o elenco principal, voluntária ou relutantemente, torna-se investigadores que devem trabalhar para responder quem, o quê, por que e/ou como do dilema...');
insert into genre (`name`, `description`) values('Sci-fi', 'Avanços tecnológicos imaginados ou ambientes naturais que atualmente são irreais, mas que poderiam ser inventados, causados ​​ou explicados pela ciência no futuro. A narrativa de histórias de ficção científicaica.');
insert into genre (`name`, `description`) values('Suspense', 'Incutir uma sensação de antecipação e entusiasmo tem prioridade e é conseguido com uma narrativa repleta de reviravoltas e pistas falsas. A incerteza está presente em cada passo do caminho, muitas vezes prolongada para obter o efeito máximo...');
insert into genre (`name`, `description`) values('Ecchi', 'Ecchi é uma gíria na língua japonesa para ações sexuais divertidas. Como adjetivo, é utilizado com o significado de “sexy”, “sujo” ou “safado”; como verbo, ecchi suru significa "fazer sexo" e, como substantivo, é usado para descrever...');

insert into manga (`name`, `description`, price, inventory, numPages, volume, id_collection, id_publisher, id_author, imageName) values('Mushoku Tensei', '"Que droga... Eu não tive uma vida satisfatória... Ah, se eu pudesse recomeçar mais uma vez a minha vida..."', 34.90, 40, 180, 1, 1, 1, 1, '0cd7de54-6f3e-4a85-8cdb-cc3218aaa47f.jpeg');
insert into manga (`name`, `description`, price, inventory, numPages, volume, id_collection, id_publisher, id_author, imageName) values('Konosuba', 'Kazuma Sato era um jovem antissocial e viciado em jogos cujo fim chegou cedo, quando perdeu a vida em um acidente...', 34.90, 50, 168, 1, 2, 1, 2, '7ec601c8-951d-4a85-b5c1-d4d0f6500004.jpeg');
insert into manga (`name`, `description`, price, inventory, numPages, volume, id_collection, id_publisher, id_author, imageName) values('Neon Genesis Evangelion Collectors Edition', 'Uma trama complexa, que aborda temas profundos como a psique humana, seus conflitos internos e relacionamentos...', 74.94, 50, 336, 1, 3, 2, 3, '44b7da0a-a3f2-46e9-b970-7ff2d34caa12.jpeg');
insert into manga (`name`, `description`, price, inventory, numPages, volume, id_collection, id_publisher, id_author, imageName) values('No Game No Life', 'Vou transmitir a vocês os romances dos cinco mais falados em Disboard, tá?!!! Em Disboard, um mundo em que tudo era decidido por jogos...', 38.90, 20, 496, 11, 4, 3, 5, 'c14e8f74-0770-4021-af00-4193fbe05735.jpeg');
insert into manga (`name`, `description`, price, inventory, numPages, volume, id_collection, id_publisher, id_author, imageName) values('Yorha: Pearl Harbor Descent Record', 'The distant future. Invaders from another world attack without warning, unleashing a new type of threat: weapons known as "machine lifeforms."...', 74.61, 55, 176, 1, 5, 4, 4, '538f3b2c-9f20-4fad-9328-ddef11743bb1.jpeg');

insert into manga_genre (id_manga, id_genre) values(1, 1);
insert into manga_genre (id_manga, id_genre) values(1, 2);
insert into manga_genre (id_manga, id_genre) values(2, 1);
insert into manga_genre (id_manga, id_genre) values(2, 2);
insert into manga_genre (id_manga, id_genre) values(2, 3);
insert into manga_genre (id_manga, id_genre) values(2, 4);
insert into manga_genre (id_manga, id_genre) values(3, 5);
insert into manga_genre (id_manga, id_genre) values(3, 6);
insert into manga_genre (id_manga, id_genre) values(3, 7);
insert into manga_genre (id_manga, id_genre) values(3, 8);
insert into manga_genre (id_manga, id_genre) values(3, 9);
insert into manga_genre (id_manga, id_genre) values(4, 1);
insert into manga_genre (id_manga, id_genre) values(4, 4);
insert into manga_genre (id_manga, id_genre) values(4, 10);
insert into manga_genre (id_manga, id_genre) values(4, 2);
insert into manga_genre (id_manga, id_genre) values(5, 5);
insert into manga_genre (id_manga, id_genre) values(5, 8);
