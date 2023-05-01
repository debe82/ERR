INSERT INTO country (id, name) VALUES (1, 'Italy');
INSERT INTO country (id, name) VALUES (2, 'France');
INSERT INTO country (id, name) VALUES (3, 'Germany');
INSERT INTO country (id, name) VALUES (4, 'Spain');
INSERT INTO country (id, name) VALUES (5, 'Sweden');


INSERT INTO city (id, name, country_id) VALUES (1, 'Rome', 1);
INSERT INTO city (id, name, country_id) VALUES (2, 'Turin', 1);
INSERT INTO city (id, name, country_id) VALUES (3, 'Paris', 2);
INSERT INTO city (id, name, country_id) VALUES (4, 'Lyon', 2);
INSERT INTO city (id, name, country_id) VALUES (5, 'Berlin', 3);
INSERT INTO city (id, name, country_id) VALUES (6, 'Bonn', 3);
INSERT INTO city (id, name, country_id) VALUES (7, 'Madrid', 4);
INSERT INTO city (id, name, country_id) VALUES (8, 'Barcellona', 4);
INSERT INTO city (id, name, country_id) VALUES (9, 'Stockholm', 5);
INSERT INTO city (id, name, country_id) VALUES (10, 'Goteborg', 5);


INSERT INTO rescued_rat
(age, breed, name, sex, spayed, city_id)
VALUES
    (12, 'Quicksilver', 'Squall', 'male', true, 1);
INSERT INTO rescued_rat
    (age, breed, name, sex,  spayed, city_id)
VALUES
    (5, 'Rex', 'Duke', 'male', true, 2);
INSERT INTO rescued_rat
(age, breed, name, sex, clinical_status_clinical_status_id, spayed, city_id)
VALUES
    (12, 'Manx', 'Doom Slayer', 'male', null, true, 3);
INSERT INTO rescued_rat
(age, breed, name, sex, clinical_status_clinical_status_id, spayed, city_id)
VALUES
    (5, 'Sphynx', 'Link', 'male', 2, false, 4);
INSERT INTO rescued_rat
(age, breed, name, sex, clinical_status_id, spayed, city_id)
VALUES
    (12, 'Satin', 'Prophet', 'male', null, false, 3);
INSERT INTO rescued_rat
(age, breed, clinical_history, name, sex, spayed, city_id)
VALUES
    (5, 'Dumbo', null, 'Zoija', 'female', false, 3);
INSERT INTO rescued_rat
(age, breed, clinical_history, name, sex, spayed, city_id)
VALUES
    (12, 'Bristle Coat', null, 'Zelda', 'female', true, 4) ON CONFLICT DO NOTHING;
INSERT INTO rescued_rat
(age, breed, clinical_history, name, sex, spayed, city_id)
VALUES
    (5, 'Topaz', null, 'Lara', 'female', true, 4) ON CONFLICT DO NOTHING;
INSERT INTO rescued_rat
(age, breed, clinical_history, name, sex, spayed, city_id)
VALUES
    (12, 'Lilac Agouti', null, 'Ellie', 'female', true, 5) ON CONFLICT DO NOTHING;
INSERT INTO rescued_rat
(age, breed, clinical_history, name, sex, spayed, city_id)
VALUES
    (5, 'Cinnamon Pearl', null, 'Ripley', 'female', true, 5) ON CONFLICT DO NOTHING;


insert into clinical_status (clinical_status_id, title, description, med_instructions)
values
(1, 'pregnant',
    'the rat is pregnant. After birth the end of lactation try to separate male from female in order to avoid multiple pregnancy.',
    'check the general health status of the mother');
insert into clinical_status (clinical_status_id, title, description, med_instructions)
values
(2, 'tumor',
    'Tumor located in the chest region. After surgery, check the stitches',
    '1 ml of THIS in the morning, 1ml of THAT in the evening');

insert into rat_statuses (rescued_rat_id, clinical_status_id)
values (1,1);
insert into rat_statuses (rescued_rat_id, clinical_status_id)
values (1,2);
insert into rat_statuses (rescued_rat_id, clinical_status_id)
values (2,2);

insert into RAT_STATUS (rat_id, status_id)
values (1,1);
insert into RAT_STATUS (rat_id, status_id)
values (3,2);