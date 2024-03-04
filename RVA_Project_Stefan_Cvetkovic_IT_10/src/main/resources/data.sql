insert into sud(id, naziv, adresa)
values(nextval('sud_seq'), 'Visi sud u Novom Sadu', 'Narodnog fronta 12');

insert into sud(id, naziv, adresa)
values(nextval('sud_seq'), 'Osnovni sud u Kragujevcu', 'Nikole Tesle 4');

insert into sud(id, naziv, adresa)
values(nextval('sud_seq'), 'Apelacioni sud u Nisu', 'Jovana Ducica 65');

insert into sud(id, naziv, adresa)
values(nextval('sud_seq'), 'Upravni sud u Beogradu', 'Kralja Aleksandra 21');

insert into ucesnik(id, ime, prezime, mbr, status)
values(nextval('ucesnik_seq'), 'Ognjen', 'Jovanovic', '1', 'neaktivan');

insert into ucesnik(id, ime, prezime, mbr, status)
values(nextval('ucesnik_seq'), 'Marko', 'Komluski', '2', 'neaktivan');

insert into ucesnik(id, ime, prezime, mbr, status)
values(nextval('ucesnik_seq'), 'Marko', 'Karapandzic', '3', 'neaktivan');

insert into ucesnik(id, ime, prezime, mbr, status)
values(nextval('ucesnik_seq'), 'Stefan', 'Cvetkovic', '4', 'aktivan');

insert into predmet(id, broj_pr, opis, datum_pocetka, aktivan, sud)
values(nextval('predmet_seq'), '1', 'Krivicno', to_date('12.12.2023.' , 'dd.mm.yyyy.'), true, 1);

insert into predmet(id, broj_pr, opis, datum_pocetka, aktivan, sud)
values(nextval('predmet_seq'), '2', 'Razvod', to_date('02.02.2023.' , 'dd.mm.yyyy.'), false, 2);

insert into predmet(id, broj_pr, opis, datum_pocetka, aktivan, sud)
values(nextval('predmet_seq'), '3', 'Testament', to_date('01.01.2023.' , 'dd.mm.yyyy.'), true, 3);

insert into predmet(id, broj_pr, opis, datum_pocetka, aktivan, sud)
values(nextval('predmet_seq'), '4', 'Poslovni spor', to_date('04.04.2023.' , 'dd.mm.yyyy.'), true, 4);

insert into rociste(id, datum_rocista, sudnica, ucesnik, predmet)
values(nextval('rociste_seq'), to_date('02.08.2023.' , 'dd.mm.yyyy.'), 'Sudnica u Nisu', 1, 1);

insert into rociste(id, datum_rocista, sudnica, ucesnik, predmet)
values(nextval('rociste_seq'), to_date('13.10.2022.' , 'dd.mm.yyyy.'), 'Sudnica u Kragujevcu', 2, 2);

insert into rociste(id, datum_rocista, sudnica, ucesnik, predmet)
values(nextval('rociste_seq'), to_date('09.09.2023.' , 'dd.mm.yyyy.'), 'Sudnica u Beogradu', 3, 3);

insert into rociste(id, datum_rocista, sudnica, ucesnik, predmet)
values(nextval('rociste_seq'), to_date('11.11.2021.' , 'dd.mm.yyyy.'), 'Sudnica u Novom Sadu', 4, 4);


