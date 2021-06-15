<template>
  <main role="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            class="first-slide"
            src="https://images.unsplash.com/photo-1576670158645-ef7b03134e32?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1086&q=80"
            alt="First slide"
          />
          <div class="container">
            <div class="carousel-caption text-left">
              <h1>Welcome to Laboni</h1>
              <p>
                Place where you can receive medical attention within few clicks.
              </p>
              <p>
                <a class="btn btn-lg btn-primary" href="#" role="button"
                  >Sign up today</a
                >
              </p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="second-slide"
            src="https://images.unsplash.com/photo-1581091014534-898da33e356e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80"
            alt="Second slide"
          />
          <div class="container">
            <div class="carousel-caption">
              <h1>Laboni offers online medicine reservation.</h1>
              <p>
                Select your favourite nearest pharmacy and avoid waiting in line. Just come at scheduled time and medicine will be in your hands within minutes.
              </p>
              <p>
                <a class="btn btn-lg btn-primary" href="#" role="button"
                  >Learn more</a
                >
              </p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="third-slide"
            src="https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80"
            alt="Third slide"
          />
          <div class="container">
            <div class="carousel-caption text-right">
              <h1>One more for good measure.</h1>
              <p>
                Hate calling to schedule appointment? Create account and choose your doctor, location and time however you prefer.
              </p>
              <p>
                <a class="btn btn-lg btn-primary" href="#" role="button"
                  >Browse gallery</a
                >
              </p>
            </div>
          </div>
        </div>
      </div>
      <a
        class="carousel-control-prev"
        href="#myCarousel"
        role="button"
        data-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a
        class="carousel-control-next"
        href="#myCarousel"
        role="button"
        data-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2>Pharmacies</h2>
          <p>
            Laboni is place where you can find all needed pharmacies in your surounding.
          </p>
          <p>
            <a class="btn btn-primary" @click="$router.push('pharmacies')" role="button"
              >View pharmacies &raquo;</a
            >
          </p>
        </div>
        <div class="col-md-4">
          <h2>Medicines</h2>
          <p>
            Your are sick? Don't worry! Laboni is the place for you! Here you can find all needed medications with just one click.
          </p>
          <p>
            <a class="btn btn-primary" @click="$router.push('medicines')" role="button"
              >View medications &raquo;</a
            >
          </p>
        </div>
        <div class="col-md-4">
          <h2>Registration</h2>
          <p>
            No account? Register here for free.
          </p>
          <p>
            <a class="btn btn-primary" @click="$router.push('registration')" role="button"
              >Register &raquo;</a
            >
          </p>
        </div>
      </div>

      <hr />
    </div>
    <!-- /container -->
  </main>
</template>

<script>
export default {
  name: "HelloWorld",
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* GLOBAL STYLES
-------------------------------------------------- */
/* Padding below the footer and lighter body text */

body {
  /* padding-top: 3rem; */
  padding-bottom: 3rem;
  color: #5a5a5a;
}

/* CUSTOMIZE THE CAROUSEL
-------------------------------------------------- */

/* Carousel base class */
.carousel {
  margin-bottom: 4rem;
}
/* Since positioning the image, we need to help out the caption */
.carousel-caption {
  bottom: 3rem;
  z-index: 10;
}

/* Declare heights because of positioning of img element */
.carousel-item {
  height: 32rem;
  background-color: #777;
}
.carousel-item > img {
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  height: 32rem;
  object-fit: cover;
}

/* MARKETING CONTENT
-------------------------------------------------- */

/* Center align the text within the three columns below the carousel */
.marketing .col-lg-4 {
  margin-bottom: 1.5rem;
  text-align: center;
}
.marketing h2 {
  font-weight: 400;
}
.marketing .col-lg-4 p {
  margin-right: 0.75rem;
  margin-left: 0.75rem;
}

/* Featurettes
------------------------- */

.featurette-divider {
  margin: 5rem 0; /* Space out the Bootstrap <hr> more */
}

/* Thin out the marketing headings */
.featurette-heading {
  font-weight: 300;
  line-height: 1;
  letter-spacing: -0.05rem;
}

/* RESPONSIVE CSS
-------------------------------------------------- */

@media (min-width: 40em) {
  /* Bump up size of carousel content */
  .carousel-caption p {
    margin-bottom: 1.25rem;
    font-size: 1.25rem;
    line-height: 1.4;
  }

  .featurette-heading {
    font-size: 50px;
  }
}

@media (min-width: 62em) {
  .featurette-heading {
    margin-top: 7rem;
  }
}
</style>
