-- Suppression des vote sur les commentaires de ce post
DELETE FROM post_comment_vote PCV
USING post_comment PC
WHERE PC.id = PCV.post_comment_id
AND PC.post_id = 2;

-- Suppression des commentaires
DELETE FROM post_comment
WHERE post_id = 2;

-- Suppression des associations avec les images
DELETE FROM post_images
WHERE post_id = 2;

-- Suppression des votes
DELETE FROM post_vote
WHERE post_id = 2;

-- Suppression des favoris
DELETE FROM user_favorite_post
WHERE post_id = 2;

-- Suppression du post
DELETE FROM post
WHERE id = 2;


-- Nettoyage post suppression...


-- Suppression des image_data_cache sans post associé
DELETE FROM image_data_cache IDC_del
USING (
    SELECT DISTINCT IDC.image_id
    FROM image_data_cache IDC
    LEFT JOIN post_images PI
    ON PI.image_id = IDC.image_id
    LEFT JOIN "user" U
    ON U.image_id = IDC.image_id
    WHERE PI.image_id IS NULL
    AND U.image_id IS NULL
) AS sub
WHERE sub.image_id = IDC_del.image_id;

-- Suppression des image sans post associé
DELETE FROM image I_del
USING (
    SELECT DISTINCT I.id
    FROM image I
    LEFT JOIN post_images PI
    ON PI.image_id = I.id
    LEFT JOIN "user" U
    ON U.image_id = I.id
    WHERE PI.image_id IS NULL
    AND U.image_id IS NULL
) AS sub
WHERE sub.id = I_del.id;

-- Suppression des data sans images
DELETE FROM image_data ID_del
USING (
    SELECT ID.id
    FROM image_data ID
    LEFT JOIN image I
    ON I.image_data_id = ID.id
    LEFT JOIN image_data_cache IDC
    ON IDC.image_data_id = ID.id
    WHERE I.image_data_id IS NULL
    AND IDC.image_data_id IS NULL
) AS sub
WHERE sub.id = ID_del.id;


-- Suppression du cache data sans data
DELETE FROM image_data_cache IDC_del
USING (
    SELECT IDC.image_id, IDC.image_data_id
    FROM image_data_cache IDC
    LEFT JOIN image_data ID
    ON ID.id = IDC.image_data_id
    WHERE ID.id IS NULL
) AS sub
WHERE sub.image_id = IDC_del.image_id
AND sub.image_data_id = IDC_del.image_data_id;