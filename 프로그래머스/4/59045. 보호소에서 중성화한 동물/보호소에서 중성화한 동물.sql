-- 코드를 입력하세요
SELECT ins.animal_id, ins.animal_type, ins.name
from animal_ins ins join animal_outs outs
using (animal_id)
where ins.sex_upon_intake  like "intact%"
and (outs.sex_upon_outcome like "Spayed%"
     or 
    outs.sex_upon_outcome like "Neutered%")