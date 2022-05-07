SELECT * FROM TB_BUYER buyer JOIN TB_ADDRESS address ON buyer.ADDRESS_ID = address.ID JOIN TB_CART cart ON buyer.ID = cart.BUYER_ID;

SELECT * FROM TB_STOCK stock JOIN TB_PRODUCT product ON stock.ID = product.STOCK_ID;

SELECT * FROM TB_CART cart JOIN TB_CART_PRODUCT cart_product ON cart.ID = CARTLIST_ID JOIN TB_PRODUCT product ON product.ID = cart_product.PRODUCTLIST_ID;