INSERT INTO public.cities (city_id, city_name) VALUES (1, 'Минск');
INSERT INTO public.cities (city_id, city_name) VALUES (2, 'Могилев');
INSERT INTO public.cities (city_id, city_name) VALUES (3, 'Брест');
INSERT INTO public.cities (city_id, city_name) VALUES (4, 'Витебск');
INSERT INTO public.cities (city_id, city_name) VALUES (5, 'Гомель');
INSERT INTO public.cities (city_id, city_name) VALUES (6, 'Гродно');

INSERT INTO public.citizenships (citizenship_id, citizenship_name) VALUES (1, 'Беларус');
INSERT INTO public.citizenships (citizenship_id, citizenship_name) VALUES (2, 'Русский');
INSERT INTO public.citizenships (citizenship_id, citizenship_name) VALUES (3, 'Украинец');
INSERT INTO public.citizenships (citizenship_id, citizenship_name) VALUES (4, 'Поляк');
INSERT INTO public.citizenships (citizenship_id, citizenship_name) VALUES (5, 'Литовец');

INSERT INTO public.disabilities (disability_id, disablity_name) VALUES (1, 'Инвалидность I группы');
INSERT INTO public.disabilities (disability_id, disablity_name) VALUES (2, 'Инвалидность II группы');
INSERT INTO public.disabilities (disability_id, disablity_name) VALUES (3, 'Инвалидность III группы');
INSERT INTO public.disabilities (disability_id, disablity_name) VALUES (4, 'Инвалидность IV группы');

INSERT INTO public.marital_statuses (marital_status_id, marital_status_name) VALUES (1, 'Женат/за мужем');
INSERT INTO public.marital_statuses (marital_status_id, marital_status_name) VALUES (2, 'Холост(а)');

INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (1, '3012', 'Текущий счет - ЮЛ', false);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (2, '3013', 'Текущий счет - ИП', false);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (3, '3014', 'Текущий счет - ФЛ', false);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (4, '2100', 'Кредитный счет - ЮЛ', true);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (5, '2300', 'Кредитный счет - ИП', true);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (6, '2400', 'Кредитный счет - ФЛ', true);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (7, '1010', 'Касса Банка', true);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (8, '7327', 'Фонд развития банка', false);
INSERT INTO public.account_type (account_type_id, account_type_code, account_type_name, active_flag) VALUES (9, '1201', 'Корреспондентский счет в НБ РБ', true);

INSERT INTO public.accounts (account_id, account_number, account_type_id, account_owner, account_balance) VALUES (1, '1111111111111', 7, null, 0);
INSERT INTO public.accounts (account_id, account_number, account_type_id, account_owner, account_balance) VALUES (2, '2222222222222', 8, null, 100000000000);
INSERT INTO public.accounts (account_id, account_number, account_type_id, account_owner, account_balance) VALUES (3, '333333333333', 9, null, 100000000);
