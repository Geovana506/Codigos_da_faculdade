import os
from flask import Flask, render_template, request

app = Flask(__name__, static_folder=os.path.join(
    os.path.dirname(__file__), "..", "static"))


@app.route("/", methods=["GET", "POST"])
def index():
    resultado = None
    erro = None

    try:
        if request.method == "POST":
            preco = float(request.form["preco"])
            desconto = float(request.form["desconto"])

            valor = (preco * desconto) / 100
            final = preco - valor

            resultado = {
                "desconto": round(valor, 2),
                "final": round(final, 2)
            }

    except ValueError:
        erro = "Digite números válidos!"

    return render_template("index.html", resultado=resultado, erro=erro)


if __name__ == "__main__":
    app.run(debug=True)
