
SELECT setval(
    pg_get_serial_sequence('conta', 'id_conta'),
    COALESCE((SELECT MAX(id_conta) FROM conta), 1)
);
SELECT setval(
    pg_get_serial_sequence('transacao', 'id'),
    COALESCE((SELECT MAX(id) FROM transacao), 1)
);