DELETE FROM post
USING post AS P
LEFT JOIN post_images PI
ON PI.post_id = P.id
WHERE P.id = post.id
AND PI.image_id IS NULL;
