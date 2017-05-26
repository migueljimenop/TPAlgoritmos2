{-
    Cada Delta esta formado por un conjunto de transiciones,
    cada transicion tiene el siguiente formato:
    ((estado del cual se parte, caracter involucrado), estado final)
-}
type Delta = [((Int,Char),Int)] 
type DFA = ([Int],[Char],Delta,Int,Int)  --(conjunto de estados, sigma, delta, estado inicial, estado final)

-- Retorna todos los subconjuntos limitada con una longitud
subconjLimitado :: Int->[a]->[[a]] 
subconjLimitado 0 _ = [[]]
subconjLimitado _ [] = []
subconjLimitado k (x:xs) = [x:ys|ys<-subconjLimitado (k-1) xs] ++subconjLimitado k xs

{-
    Genera todos los posibles deltas con el formato de el tipo definido anteriormente
    para una cantidad maxima de estados y un sigma que toma como parametro.
-}
generateDelta :: [Int] ->[Char] -> Delta
generateDelta xs ys = reverse [((r,t),p)|r <- xs, t<-ys, p<-xs]

-- Genera todas las posibles combinaciones de deltas posibles dada una cantidad de estados posibles.
generateDeltas :: Int -> Delta -> [Delta]
generateDeltas 0 f = []
generateDeltas n f = (subconjLimitado n f) ++ (generateDeltas (n-1) f)


{-
    Hace la prueba sobre un delta  para cada caracter del string a reconocer(o no).
    Dado un caracter, un estado inicial y un delta retorna true si el caracter es reconocido por ese delta
    y tambien retorna el estado final en el cual queda luego de la evaluacion.
-}
pruebaDelta :: Char -> Int -> Delta -> (Bool,Int) 
pruebaDelta x n [] = (False,n)
pruebaDelta x n d
                             | (n,x) == fst d1 = (True,(snd d1))  
                             | otherwise     = ( (False || fst (pruebaDelta x n (tail d))),snd (pruebaDelta x n (tail d))) 
                         where d1 = head d

-- Funcion que toma un caracter y una cadena y determina si ese caracter pertenece a la misma.
pertenece :: Char -> String -> Bool
pertenece x [] = False
pertenece x (y:ys)   
                              | x == y  = True
                              | otherwise = pertenece x ys
{-
    Funcion que en base a una cadena, un sigma, un estado inicial, un estado final y un delta,
    determina si la cadena es reconocido por el delta. 
-}
recibe :: String -> [Char] -> Int ->Int -> Delta -> Bool 
recibe [] e ei ef d = (ei == ef)
recibe (x:xs) e ei ef d 
      | pertenece x e = ((fst st)) && (recibe xs e (snd st) ef d)  
      | otherwise        = False
      where st = pruebaDelta x ei d
{-
    Funcion que evalua si una cadena cumple con un delta en particular
    Los parametros que recibe son: 
    conjunto de cadenas -> sigma -> estado inicial -> estado final -> delta
-}
evaluaCadena :: [String] -> [Char] -> Int ->Int -> Delta -> Bool 
evaluaCadena [] _ _ _ _ = True
evaluaCadena (x:xs) sigma ei ef delta = (recibe x sigma ei ef delta) && (evaluaCadena xs sigma ei ef delta)

-- Funcion que evalua si una de las transiciones de un delta es valida para ser determinista.
validaDelta :: ((Int,Char),Int) -> Delta -> Bool
validaDelta (x1,y1) []     = True
validaDelta (x1,y1) (y:ys) 
                                | x1 == (fst y) = False
                                | otherwise =  (validaDelta (x1,y1) ys) 

-- Funcion que dado un delta, determina si todas las transisciones del mismo lo hacen determinista o no.
filtraDeltas :: Delta -> Bool
filtraDeltas [] = True
filtraDeltas (x:xs) 
                | (length xs) == 0  = True 
                | otherwise  = (validaDelta x xs) && filtraDeltas xs
{-
    Funcion que busca el primer delta que verifique que se cumplan todas las cadenas positivas y no las negativas
    La funcion toma como parametros:
     sigma -> cadena de los "pos" -> cadena de los "neg" -> cantidad maxima de estados -> conjunto de todos los delta
-}
encuentraDelta :: [Char] -> [String] -> [String] -> Int -> [Delta] -> Delta 
encuentraDelta _ _ _ _ [] = []
encuentraDelta sigma pos neg k (x:xs)
                                                | (evaluaCadena pos sigma 0 (k-1) x ) && (not(evaluaCadena neg sigma 0 (k-1) x )) = x 
                                                | otherwise = encuentraDelta sigma pos neg k xs

{- 
  Funcion que busca el minimo delta que cumpla con las restricciones de ser valido para los pos y no para los neg para 
  el menor numero de estados posibles empezando de un unico estado (representado como el estado "0"), si no 
  encuentra ninguno, retorna el delta vacio.
  Retorna el delta encontrado y el estado final que posee el automata.
-}
minimoDelta :: [Char] -> [String] -> [String] -> Int -> Int -> [Delta] -> (Delta,Int) 
minimoDelta sigma pos neg n k b
                                            | ((encuentraDelta sigma pos neg n b) == []) && (n<k) = (minimoDelta sigma pos neg (n+1) k b)
                                            | otherwise = ((encuentraDelta sigma pos neg n b),n) --retorna el delta vacio

{-
    Funcion principal del programa con el cual se obtiene un automata (DFA) 
    La funcion recibe los parametros de la siguiente forma: 
      sigma -> lista de estados "pos", lista de estados "neg"-> cantidad maxima de estados k
-}
check :: [Char] -> [String] -> [String] -> Int -> DFA 
check sigma pos neg k = 
                                        let 
                                          a = generateDelta [0..k] sigma
                                          b = filter (filtraDeltas)(reverse (generateDeltas k a)) -- Se aplica el reverse con el fin de empezar 
                                          c = (minimoDelta sigma pos neg 0 k b)                      -- las evaluaciones con la minima cantidad 
                                        in                                                                                   --  de estados posibles    
                                          ([0..(k-1)],sigma,(fst c),0,((snd c)-1))