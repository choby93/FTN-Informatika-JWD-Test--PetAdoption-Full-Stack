INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO kategorija(id, naziv) VALUES (1, "pas");
INSERT INTO kategorija(id, naziv) VALUES (2, "mačka");
INSERT INTO kategorija(id, naziv) VALUES (3, "kornjača");
INSERT INTO kategorija(id, naziv) VALUES (4, "hrčak");
INSERT INTO kategorija(id, naziv) VALUES (5, "kameleon");

INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) 
			VALUES (1, "Bella", 48, true, "ŽENSKI", 24.3, "Bull terrier", 1);
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) 
			VALUES (2, "Nevidljivi", 25, false, "MUŠKI", 8.3, "Kameleoni (Chamaeleonidae) je familija guštera koji je verovatno najbolje poznata po sposobnosti promene boje tela.", 5);          
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) 
			VALUES (4, "Debeli", 9, true, "MUŠKI", 0.56, "Hrčci (lat. Cricetinae) su potporodica glodara iz porodice hrčkova (lat. Cricetidae).", 4);           
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) 
			VALUES (5, "Mirka", 14, true, "ŽENSKI", 2.88, "Britanska kratkodlaka mačka", 2);
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) 
			VALUES (3, "Brzi Konzales", 19, false, "MUŠKI", 0.45, "MORSKA KOŽASTA KORNJAČA", 3);

insert INTO udomljavanje(id, datum) VALUES (1, '2023-4-30'); 
insert INTO udomljavanje(id, datum) VALUES (2, '2023-1-22'); 
insert INTO udomljavanje(id, datum) VALUES (3, '2023-2-12'); 