insert into account_type(id, name, is_charged) values(1, 'CONTA-CORRENTE',true);
insert into account_type(id, name, is_charged) values(2, 'CONTA-CORRENTE-EXCLUSIVE',false);

insert into tax(id,tax_value) values(1,0);
insert into tax(id,tax_value) values(2,0.4);
insert into tax(id,tax_value) values(3,1.458);

insert into comparator(id,comparator_value,initial_value,final_value) values(1,'<',-1,null);
insert into comparator(id,comparator_value,initial_value,final_value) values(2,'>',1,null);
insert into comparator(id,comparator_value,initial_value,final_value) values(3,'<=',-1,0);
insert into comparator(id,comparator_value,initial_value,final_value) values(4,'>=',1,0);

insert into interval_tax(id, interval_value, comparator_id,tax_id, interval_type ) values(1,100,3,1,'INITIAL');
insert into interval_tax(id, interval_value, comparator_id,tax_id, interval_type ) values(2,100,2,2,'INITIAL');
insert into interval_tax(id, interval_value, comparator_id,tax_id, interval_type ) values(3,300,3,2,'FINAL');
insert into interval_tax(id, interval_value, comparator_id,tax_id, interval_type ) values(4,300,2,3,'INITIAL');


