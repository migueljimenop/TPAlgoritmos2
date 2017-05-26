type Delta = [((Int,Char),Int)]
type DFA = ([Int],[Char],Delta,Int,Int)  --(qs,sigma,delta,qi,qf)

generateDelta :: [Int] ->[Char] -> Delta
generateDelta xs ys = reverse [((r,t),p)|r <- xs, t<-ys, p<-xs]

--Retorna todos los subconjuntos limitada con una longitud
subconjLimitado :: Int->[a]->[[a]] 
subconjLimitado 0 _ = [[]]
subconjLimitado _ [] = []
subconjLimitado k (x:xs) = [x:ys|ys<-subconjLimitado (k-1) xs] ++subconjLimitado k xs

generateDeltas :: Int -> Delta -> [Delta]
generateDeltas 0 f = []
generateDeltas n f = (subconjLimitado n f) ++ (generateDeltas (n-1) f)

-- hacer prueba delta para cada caracter del string a reconocer(o no)

pruebaDelta :: Char -> Int -> Delta -> (Bool,Int) -- dado un char un ei y un delta retorna true si el char es reconocible por esos delta
-- y el estado final en que quedo el automata
pruebaDelta x n [] = (False,n)
pruebaDelta x n d
      | (n,x) == fst d1 = (True,(snd d1))  
      | otherwise     = ( (False || fst (pruebaDelta x n (tail d))),snd (pruebaDelta x n (tail d)))  -- reacomodar con let in
      where d1 = head d


recibe :: String -> [Char] -> Int ->Int -> Delta -> Bool   --(cadena,sigma,ei,ef,delta)
recibe [] e ei ef d = (ei == ef)
recibe (x:xs) e ei ef d 
      | pertenece x e = ((fst st)) && (recibe xs e (snd st) ef d)  
      | otherwise        = False
      where st = pruebaDelta x ei d

pertenece :: Char -> String -> Bool
pertenece x [] = False
pertenece x (y:ys)   
      | x == y  = True
      | otherwise = pertenece x ys

-- reverse generateDeltas k 

check :: [Char] -> [String] -> [String] -> Int -> DFA --(sigma,pos,neg,k)
-- controlar el caso de que sea vacio y decir que no se puede generar el automata.
check sigma pos neg k = 
            let 
              a = generateDelta [0..k] sigma
              b = filter (filtro)(reverse (generateDeltas k a))
              c = (check1 sigma pos neg 0 k b)
            in
              ([0..(k-1)],sigma,(fst c),0,((snd c)-1))


check1 :: [Char] -> [String] -> [String] -> Int -> Int -> [Delta] -> (Delta,Int) --(sigma,pos,neg,k)
check1 sigma pos neg n k b
                        | ((check2 sigma pos neg n b) == []) && (n<k) = (check1 sigma pos neg (n+1) k b)
                        | otherwise = ((check2 sigma pos neg n b),n)
              

eval :: [String] -> [Char] -> Int ->Int -> Delta -> Bool 
eval [] _ _ _ _ = True
eval (x:xs) sigma ei ef delta = (recibe x sigma ei ef delta) && (eval xs sigma ei ef delta)

check2 :: [Char] -> [String] -> [String] -> Int -> [Delta] -> Delta 
check2 _ _ _ _ [] = []
check2 sigma pos neg k (x:xs)
              | (eval pos sigma 0 (k-1) x ) && (not(eval neg sigma 0 (k-1) x )) = x 
              | otherwise = check2 sigma pos neg k xs

filtro :: Delta -> Bool
filtro [] = True
filtro (x:xs) | (length xs) == 0  = True 
        | otherwise  = (validar x xs) && filtro xs

validar :: ((Int,Char),Int) -> Delta -> Bool
validar (x1,y1) []     = True
validar (x1,y1) (y:ys) | x1 == (fst y) = False
             | otherwise =  (validar (x1,y1) ys) 