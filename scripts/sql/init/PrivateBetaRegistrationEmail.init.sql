-- DELETE FROM "admin".private_beta_registration_email;

-- ALTER SEQUENCE "public".seq_private_beta_registration_email RESTART;

INSERT INTO "admin".private_beta_registration_email
(id, version, date_created, last_updated, email)
VALUES
(nextval('seq_private_beta_registration_email'), 0, now(), now(), 'email@provider.com');