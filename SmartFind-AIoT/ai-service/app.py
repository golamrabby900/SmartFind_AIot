from flask import Flask, jsonify, request
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = Flask(__name__)

@app.get("/health")
def health():
    return jsonify({"status": "ok", "service": "SmartFind matching baseline"})

@app.post("/match")
def match():
    payload = request.get_json(silent=True) or {}
    query = payload.get("query", "")
    candidates = payload.get("candidates", [])

    if not isinstance(query, str) or not query.strip():
        return jsonify({"error": "query must be a non-empty string"}), 400
    if not isinstance(candidates, list):
        return jsonify({"error": "candidates must be a list"}), 400

    cleaned = []
    for item in candidates:
        if not isinstance(item, dict):
            continue
        item_id = item.get("id")
        text = item.get("text", "")
        if item_id is None or not isinstance(text, str) or not text.strip():
            continue
        cleaned.append({"id": str(item_id), "text": text.strip()})

    if not cleaned:
        return jsonify({"matches": [], "note": "No valid candidates provided."})

    corpus = [query.strip()] + [item["text"] for item in cleaned]
    matrix = TfidfVectorizer(stop_words="english", ngram_range=(1, 2)).fit_transform(corpus)
    scores = cosine_similarity(matrix[0:1], matrix[1:]).flatten()
    ranked = sorted(
        [{"id": item["id"], "text": item["text"], "score": round(float(score), 4)}
         for item, score in zip(cleaned, scores)],
        key=lambda row: row["score"],
        reverse=True,
    )
    return jsonify({
        "matches": ranked,
        "warning": "Similarity is advisory only; authorized humans must verify ownership."
    })

if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5001, debug=True)
