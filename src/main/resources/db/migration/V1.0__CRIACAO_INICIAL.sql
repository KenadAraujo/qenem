CREATE TABLE public.questao (
                                id bigserial NOT NULL,
                                quantidade_alternativa int4 NULL,
                                CONSTRAINT questao_pkey PRIMARY KEY (id)
);

CREATE TABLE public.banca (
                              id bigserial NOT NULL,
                              nome varchar(255) NULL,
                              CONSTRAINT banca_pkey PRIMARY KEY (id)
);

CREATE TABLE public.alternativa (
                                    id bigserial NOT NULL,
                                    imagem bytea NULL,
                                    "label" varchar(255) NULL,
                                    ordem int4 NULL,
                                    texto varchar(255) NULL,
                                    questao_id int8 NULL,
                                    CONSTRAINT alternativa_pkey PRIMARY KEY (id)
);

ALTER TABLE public.alternativa ADD CONSTRAINT fk_alternativa_questao
    FOREIGN KEY (questao_id) REFERENCES public.questao(id);

CREATE TABLE public.caderno (
                                id bigserial NOT NULL,
                                ano int4 NULL,
                                banca_id int8 NOT NULL,
                                CONSTRAINT caderno_pkey PRIMARY KEY (id)
);

ALTER TABLE public.caderno ADD CONSTRAINT fk_caderno_banca
    FOREIGN KEY (banca_id) REFERENCES public.banca(id);

CREATE TABLE public.caderno_questao (
                                        fk_questao int8 NOT NULL,
                                        fk_caderno int8 NOT NULL,
                                        CONSTRAINT caderno_questao_pkey PRIMARY KEY (fk_questao, fk_caderno)
);

ALTER TABLE public.caderno_questao ADD CONSTRAINT fk_caderno_questao_questao
    FOREIGN KEY (fk_questao) REFERENCES public.questao(id);
ALTER TABLE public.caderno_questao ADD CONSTRAINT fk_caderno_questao_caderno
    FOREIGN KEY (fk_caderno) REFERENCES public.caderno(id);

CREATE TABLE public.texto_apoio (
                                    id bigserial NOT NULL,
                                    imagem int2 NULL,
                                    ordem int4 NULL,
                                    texto varchar(255) NULL,
                                    questao_id int8 NULL,
                                    CONSTRAINT texto_apoio_pkey PRIMARY KEY (id)
);

ALTER TABLE public.texto_apoio ADD CONSTRAINT fk_texto_apoio_questao
    FOREIGN KEY (questao_id) REFERENCES public.questao(id);

-- MIGRAÇÃO DE DADOS
INSERT INTO public.banca VALUES (1,'INEP/ENEM');