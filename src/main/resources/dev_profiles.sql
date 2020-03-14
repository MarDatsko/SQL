CREATE DATABASE dev_profiles_db
WITH OWNER = postgres

CREATE TABLE public.accounts
(
  id bigint,
  first_name character varying(40),
  last_name character varying(40),
  city character varying(40),
  gender character varying(40),
  username character varying(40)
);

CREATE TABLE public.profiles_table
(
  id bigint,
  username character varying(40),
  job_title character varying(40),
  department character varying(40),
  company character varying(40),
  skill character varying(40)
);

ALTER TABLE profiles_table RENAME TO profiles;
--1
SELECT department FROM profiles ORDER BY skill;
--2
SELECT job_title FROM profiles GROUP BY job_title HAVING COUNT(job_title) > 3;
--3
SELECT first_name,last_name,job_title,company FROM accounts INNER JOIN profiles ON accounts.id = profiles.id ORDER BY (city);
--4
SELECT department, COUNT(username) FROM profiles GROUP BY department ORDER BY COUNT(username) DESC limit 1;
--5

--6
SELECT * FROM accounts ORDER BY city;
--7
UPDATE dev_profiles_db.public.profiles SET job_title = REPLACE(job_title, 'Engineer', 'Developer') WHERE job_title LIKE '%Engineer%';

