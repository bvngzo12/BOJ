-- 코드를 입력하세요
SELECT i.ingredient_type, sum(f.total_order) total_order
from first_half f join icecream_info i
using (flavor)
group by i.ingredient_type