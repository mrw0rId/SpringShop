INSERT INTO `products` (`price`, `productName`)
    VALUE   ('60000', 'YamahaDTX400'),
        ('22000', 'fender-ym60'),
        ('25000','Sony WF-1000x3'),
        ('66000','KORG KROSS2-61-MB');
GO

INSERT INTO `categories` (`category`)
VALUE ('guitar'), ('drums'), ('recorders'), ('winds'), ('headphones'), ('keyboards');
GO

INSERT INTO `products_categories`(`product_id`, `category_id`)
SELECT (SELECT id FROM `products` WHERE `productName` = 'YamahaDTX400'), (SELECT id FROM `categories` WHERE `category` = 'drums')
UNION ALL
SELECT (SELECT id FROM `products` WHERE `productName` = 'fender-ym60'), (SELECT id FROM `categories` WHERE `category` = 'guitar')
UNION ALL
SELECT (SELECT id FROM `products` WHERE `productName` = 'Sony WF-1000x3'), (SELECT id FROM `categories` WHERE `category` = 'headphones')
UNION ALL
SELECT (SELECT id FROM `products` WHERE `productName` = 'KORG KROSS2-61-MB'), (SELECT id FROM `categories` WHERE `category` = 'keyboards');
GO