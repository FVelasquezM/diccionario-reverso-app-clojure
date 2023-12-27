# diccionario-reverso-app-clojure

A small app that, given a phrase, searches for the words whose definition most closely match the word (in spanish).

The app expects to receive a http POST request with a JSON containing a phrase. For example: 

    {
     "phrase": "de corta duración"
    }

And will respond with the words with the closest definitions: 
    
    {
        "results": [
            {
                "score": 0.999998033,
                "word": "braquícero",
                "definition": "de corta duración"
            }
        ... more results
        ],
        "origin": "Clojure Server"
    }
    
