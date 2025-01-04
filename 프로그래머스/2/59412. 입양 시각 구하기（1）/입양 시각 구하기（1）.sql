-- 코드를 입력하세요
SELECT Hour(datetime) Hour, count(*) count
from animal_outs
where Hour(datetime) >= 9 and Hour(datetime) <= 19
group by hour
order by hour