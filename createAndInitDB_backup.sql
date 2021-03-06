--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

-- Started on 2021-10-02 20:25:45

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 200 (class 1259 OID 16423)
-- Name: guests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.guests (
    id integer NOT NULL,
    lastname character varying,
    name character varying,
    middle_name character varying,
    age integer
);


ALTER TABLE public.guests OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16491)
-- Name: guests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.guests ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.guests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 202 (class 1259 OID 16435)
-- Name: reserves; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reserves (
    id integer NOT NULL,
    check_in timestamp with time zone,
    check_out timestamp with time zone,
    room_id integer,
    guest_id integer
);


ALTER TABLE public.reserves OWNER TO postgres;

--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE reserves; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.reserves IS 'Хранятся интервалы бронирования (резервирования) комнат';


--
-- TOC entry 203 (class 1259 OID 16482)
-- Name: reserves_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reserves ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reserves_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 201 (class 1259 OID 16426)
-- Name: rooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rooms (
    id integer NOT NULL,
    class_of_accommodations character varying
);


ALTER TABLE public.rooms OWNER TO postgres;

--
-- TOC entry 3001 (class 0 OID 16423)
-- Dependencies: 200
-- Data for Name: guests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.guests (id, lastname, name, middle_name, age) FROM stdin;
1	Иванов	Иван	Иванович	12
100	Тестер	Тесторович	Тестеров	20
\.


--
-- TOC entry 3003 (class 0 OID 16435)
-- Dependencies: 202
-- Data for Name: reserves; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reserves (id, check_in, check_out, room_id, guest_id) FROM stdin;
23	2021-09-29 22:01:00+03	2021-09-29 22:01:00+03	201	1
\.


--
-- TOC entry 3002 (class 0 OID 16426)
-- Dependencies: 201
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rooms (id, class_of_accommodations) FROM stdin;
404	suite
401	suite
201	comfort
405	suite
202	comfort
301	suite
102	standart
\.


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 204
-- Name: guests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.guests_id_seq', 4, true);


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 203
-- Name: reserves_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reserves_id_seq', 23, true);


--
-- TOC entry 2864 (class 2606 OID 16444)
-- Name: guests guests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.guests
    ADD CONSTRAINT guests_pkey PRIMARY KEY (id);


--
-- TOC entry 2868 (class 2606 OID 16439)
-- Name: reserves reserves_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserves
    ADD CONSTRAINT reserves_pkey PRIMARY KEY (id);


--
-- TOC entry 2866 (class 2606 OID 16446)
-- Name: rooms rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);


--
-- TOC entry 2870 (class 2606 OID 16503)
-- Name: reserves guest_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserves
    ADD CONSTRAINT guest_id_fk FOREIGN KEY (guest_id) REFERENCES public.guests(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2869 (class 2606 OID 16498)
-- Name: reserves room_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserves
    ADD CONSTRAINT room_id_fk FOREIGN KEY (room_id) REFERENCES public.rooms(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 200
-- Name: TABLE guests; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.guests TO rrs_user;


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE reserves; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.reserves TO rrs_user;


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 201
-- Name: TABLE rooms; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.rooms TO rrs_user;


--
-- TOC entry 1723 (class 826 OID 16431)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO rrs_user;


-- Completed on 2021-10-02 20:25:46

--
-- PostgreSQL database dump complete
--

