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
            c = float(request.form["celsius"])
            f = c * 1.8 + 32
            resultado = f"{c} °C é igual a {round(f, 2)} °F"
    except ValueError:
        erro = "Digite um número válido!"

    return render_template("index.html", resultado=resultado, erro=erro)


if __name__ == "__main__":
    app.run(debug=True)
