-- 코드를 입력하세요
SELECT fd.product_id, fd.product_name, sum(fd.price*fo.amount) total_sales
from food_product fd join food_order fo
using(product_id)
where year(fo.produce_date)=2022 and month(fo.produce_date)=5
group by fd.product_id
order by total_sales desc, fd.product_id