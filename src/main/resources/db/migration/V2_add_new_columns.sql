ALTER TABLE public.conta ADD COLUMN razao_social VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN cnpj VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN agencia VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN numero_conta VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN limite_cheque_especial NUMERIC(10,2);
ALTER TABLE public.conta ADD COLUMN data_abertura DATE;
ALTER TABLE public.conta ADD COLUMN ativa BOOLEAN;
ALTER TABLE public.conta ADD COLUMN data_aniversario DATE;